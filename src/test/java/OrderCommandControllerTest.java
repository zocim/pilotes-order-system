/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tui.proof.dtos.CreateOrderCommand;
import com.tui.proof.dtos.OrderDTO;
import com.tui.proof.dtos.OrderUpdateCommand;
import com.tui.proof.services.command.OrderCommandService;
import com.tui.proof.ws.controller.command.OrderCommandController;

import reactor.core.publisher.Mono;

/**
 * @author Created by Maneva.
 * @since 3.6.24.
 */

@WebFluxTest(OrderCommandController.class)
@ExtendWith(MockitoExtension.class)
public class OrderCommandControllerTest {

    @Mock
    private OrderCommandService orderCommandService;

    @InjectMocks
    private OrderCommandController orderCommandController;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCreateOrder() {
        CreateOrderCommand command = new CreateOrderCommand();
        OrderDTO expectedOrderDTO = new OrderDTO();

        when(orderCommandService.handleCreateOrder(any())).thenReturn(Mono.just(expectedOrderDTO));

        webTestClient.post()
            .uri("/api/v1/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(command), CreateOrderCommand.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(OrderDTO.class)
            .isEqualTo(expectedOrderDTO);
    }

    @Test
    public void testUpdateOrder() {
        String orderNumber = "123";
        OrderUpdateCommand command = new OrderUpdateCommand();
        OrderDTO expectedOrderDTO = new OrderDTO();

        when(orderCommandService.handleUpdateOrder(any())).thenReturn(Mono.just(expectedOrderDTO));

        webTestClient.put()
            .uri("/api/v1/orders/{number}", orderNumber)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(command), OrderUpdateCommand.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(OrderDTO.class)
            .isEqualTo(expectedOrderDTO);
    }

}
