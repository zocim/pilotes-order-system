/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.services.command;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tui.proof.dtos.CreateOrderCommand;
import com.tui.proof.dtos.OrderDTO;
import com.tui.proof.dtos.OrderUpdateCommand;
import com.tui.proof.events.OrderEvent;
import com.tui.proof.events.OrderStatus;
import com.tui.proof.mappers.OrderMapper;
import com.tui.proof.model.Order;
import com.tui.proof.repositories.OrderRepository;

import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Service
public class OrderCommandService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final String ORDER_TOPIC = "orders";

    public Mono<OrderDTO> handleCreateOrder(CreateOrderCommand command) {
        Order order = orderMapper.mapToEntity(command);
        order.setCreatedTime(LocalDateTime.now());
        order.setUpdatable(true);
        return orderRepository.save(order)
            .flatMap(savedOrder -> {
                OrderEvent event = new OrderEvent();
                event.setNumber(savedOrder.getNumber());
                event.setStatus(OrderStatus.CREATED);
                event.setOrder(savedOrder);
                kafkaTemplate.send(ORDER_TOPIC, event);
                return Mono.just(orderMapper.mapToDTO(savedOrder));
            });
    }

    public Mono<OrderDTO> handleUpdateOrder(OrderUpdateCommand command) {
        return orderRepository.findById(command.getNumber())
            .flatMap(order -> {
                if(order.isUpdatable() && LocalDateTime.now().isBefore(order.getCreatedTime().plusMinutes(5))) {
                    orderMapper.updateEntity(order, command);
                    order.setUpdatedTIme(LocalDateTime.now());

                    return orderRepository.save(order)
                        .flatMap(updatedOrder -> {
                            OrderEvent event = new OrderEvent();
                            event.setNumber(order.getNumber());
                            event.setStatus(OrderStatus.UPDATED);
                            event.setOrder(updatedOrder);
                            kafkaTemplate.send(ORDER_TOPIC, event);
                            return Mono.just(orderMapper.mapToDTO(updatedOrder));
                        });
                }
                else {
                    return Mono.empty();
                }
            });
    }
}
