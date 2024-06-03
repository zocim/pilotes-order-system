/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import com.tui.proof.secuirty.JwtAuthenticationManager;
import com.tui.proof.secuirty.JwtAuthenticationWebFilter;

import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 3.6.24.
 */

@Configuration
@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    // @Bean
    // public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    //     http
    //         .authorizeExchange()
    //         .pathMatchers("/orders/search").authenticated()
    //         .anyExchange().permitAll()
    //         .and()
    //         .httpBasic().disable()
    //         .formLogin().disable()
    //         .csrf().disable()
    //         .addFilterAt(new JwtAuthenticationWebFilter(authenticationManager()), SecurityWebFilterChain.FIRST);
    //     return http.build();
    // }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .pathMatchers("/api/v1/orders/search").authenticated()
            .anyExchange().permitAll()
            .and()
            .httpBasic()
            .and()
            .formLogin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();
        return username -> {
            if (username.equals("user")) {
                return Mono.just(user);
            } else {
                return Mono.empty();
            }
        };
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

}
