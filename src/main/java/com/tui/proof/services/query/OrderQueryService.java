/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.services.query;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.tui.proof.model.Order;
import com.tui.proof.repositories.OrderRepository;

import reactor.core.publisher.Flux;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */

@Service
public class OrderQueryService {
    @Autowired
    private OrderRepository orderRepository;

    public Flux<Order> searchOrders(String search) {
        return Flux.concat(orderRepository.findByClientFirstNameContainingIgnoreCase(search)
            .concatWith(orderRepository.findByClientLastNameContainingIgnoreCase(search))
            .concatWith(orderRepository.findByClientTelephoneContaining(search)));
    }
}
