/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.sagas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tui.proof.events.OrderEvent;
import com.tui.proof.model.Order;
import com.tui.proof.repositories.OrderRepository;

import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Service
public class OrderSagaListener {

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void handleOrderEvent(OrderEvent event) {
        if("CREATED".equals(event.getStatus())) {
            handleOrderCreatedEvent(event.getOrder());
        }
        else if ("UPDATED".equals(event.getStatus())) {
            handleOrderUpdatedEvent(event.getOrder());
        }
    }

    private void handleOrderCreatedEvent(Order order) {
        Mono<Order> orderMono = orderRepository.save(order);
        orderMono.subscribe(savedOrder -> {
            System.out.println("Order created and saved: " + savedOrder);
        });
    }

    private void handleOrderUpdatedEvent(Order order) {
        Mono<Order> orderMono = orderRepository.findById(order.getNumber())
            .flatMap(existingOrder -> {
                existingOrder.setPilotes(order.getPilotes());
                existingOrder.setOrderTotal(order.getOrderTotal());
                existingOrder.setDeliveryAddress(order.getDeliveryAddress());
                return orderRepository.save(existingOrder);
            });

        orderMono.subscribe(updateOrder -> {
            System.out.println("Order updated and saved: " + updateOrder);
        });
    }
}
