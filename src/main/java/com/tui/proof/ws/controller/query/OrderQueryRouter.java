/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.ws.controller.query;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tui.proof.model.Order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */

@Configuration
public class OrderQueryRouter {

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    @RouterOperations({
        @RouterOperation(
            path = "/api/v1/orders/search",
            produces = {
                MediaType.TEXT_EVENT_STREAM_VALUE
            },
            method = RequestMethod.GET,
            beanClass = OrderQueryHandler.class,
            beanMethod = "searchOrders",
            operation = @Operation(
                operationId = "searchOrders",
                responses = {
                    @ApiResponse(
                        responseCode = "200",
                        description = "Successful searching",
                        content = @Content(schema = @Schema(
                            implementation = Order.class
                        ))
                    ),
                    @ApiResponse(
                        responseCode = "400",
                        description = "Access for search operation is forbidden"
                    )
                }
            )
        )
    })
    public RouterFunction<ServerResponse> orderRoutes(OrderQueryHandler handler) {
        return route(GET("/api/v1/orders/search"), handler::searchOrders);
    }
}
