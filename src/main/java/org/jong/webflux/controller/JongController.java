package org.jong.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class JongController {


    @GetMapping("/jong")
    public Mono<String> helloWorld(){
        return Mono.just("hello_world");
    }
}
