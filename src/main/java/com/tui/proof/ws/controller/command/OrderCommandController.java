/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.ws.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.dtos.CreateOrderCommand;
import com.tui.proof.dtos.OrderDTO;
import com.tui.proof.dtos.OrderUpdateCommand;
import com.tui.proof.services.command.OrderCommandService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */

@RestController
@RequestMapping("/api/v1/orders")
public class OrderCommandController {

    @Autowired
    private OrderCommandService orderCommandService;

    @PostMapping
    public Mono<OrderDTO> createOrder(@Valid @RequestBody CreateOrderCommand command) {
        return orderCommandService.handleCreateOrder(command);
    }

    @PutMapping("/{number}")
    public Mono<OrderDTO> updateOrder(@PathVariable String number, @Valid @RequestBody OrderUpdateCommand command) {
        command.setNumber(number);
        return orderCommandService.handleUpdateOrder(command);
    }
}
