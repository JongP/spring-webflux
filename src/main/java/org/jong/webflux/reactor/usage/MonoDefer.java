package org.jong.webflux.reactor.usage;

import org.jong.webflux.SandBox;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Primary
@Component
public class MonoDefer implements SandBox {
    @Override
    public void start() {
        Mono.just(supplyString()).subscribe(System.out::println);
        Mono.defer(this::supplyMonoString).subscribe(System.out::println);
    }


    public String supplyString(){
        return "string";
    }

    public Mono<String> supplyMonoString(){
        return Mono.just("string");
    }
}
