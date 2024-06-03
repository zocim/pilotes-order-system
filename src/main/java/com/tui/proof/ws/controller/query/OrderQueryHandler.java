/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.ws.controller.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tui.proof.dtos.OrderDTO;
import com.tui.proof.mappers.OrderMapper;
import com.tui.proof.model.Order;
import com.tui.proof.services.query.OrderQueryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Component
public class OrderQueryHandler {
    private OrderQueryService orderQueryService;
    private OrderMapper mapper;

    public OrderQueryHandler(final OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @PreAuthorize("hasRole('USER')")
    public Mono<ServerResponse> searchOrders(ServerRequest request) {
        return ReactiveSecurityContextHolder.getContext()
            .flatMap(context -> {
                String search = request.queryParam("search").orElse("");
                Flux<OrderDTO> orderDTos = orderQueryService.searchOrders(search)
                    .map(order -> mapper.mapToDTO(order));
                return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(orderDTos, Order.class);
            })
            .switchIfEmpty(ServerResponse.status(HttpStatus.FORBIDDEN).build());
    }
}
