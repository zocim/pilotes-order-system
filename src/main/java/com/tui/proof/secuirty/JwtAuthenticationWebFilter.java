/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.secuirty;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 3.6.24.
 */
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.setServerAuthenticationConverter(new ServerAuthenticationConverter() {
            @Override
            public Mono<Authentication> convert(ServerWebExchange exchange) {
                String token = extractToken(exchange);
                return Mono.justOrEmpty(token).map(t -> new UsernamePasswordAuthenticationToken(t, t));
            }

            private String extractToken(ServerWebExchange exchange) {
                return exchange.getRequest().getHeaders().getFirst("Authorization");
            }
        });
    }
}
