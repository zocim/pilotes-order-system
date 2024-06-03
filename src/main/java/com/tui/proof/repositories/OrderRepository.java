package com.tui.proof.repositories;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.tui.proof.model.Order;

import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, String> {
    Flux<Order> findByClientFirstNameContainingIgnoreCase(String search);
    Flux<Order> findByClientLastNameContainingIgnoreCase(String search);
    Flux<Order> findByClientTelephoneContaining(String search);
    //public interface OrderRepository extends R2dbcRepository<> {


}
