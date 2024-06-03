package com.tui.proof.ws.controller;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tui.proof.model.Order;

@Log4j2
@RestController
public class FooController {

  @GetMapping("/")
  void test() {
    log.info("Foo controller");
  }
}
