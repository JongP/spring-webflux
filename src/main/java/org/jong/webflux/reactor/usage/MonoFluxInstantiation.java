package org.jong.webflux.reactor.usage;

import java.time.Duration;
import java.util.Arrays;

import org.jong.webflux.SandBox;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonoFluxInstantiation implements SandBox {
    @Override
    public void start() {
        counter().subscribe(System.out::println); //not blocked
        //run by dedicated thread pool
        //It doesn't enforce a single event loop thread by default
        //Its concurrency model is based on the Publisher-Subscriber pattern and the Reactive Streams specification. not follow Reactor Pattern


        monoWithNoSignal().subscribe(System.out::println);

    }

    private Flux<String> emptyFlux(){
        return Flux.empty();
    }

    private Flux<String> fooBarFromValueFlux(){
        return Flux.just("foo","bar");
    }

    private Flux<String> fooBarFromArrayFlux(){
        String[] array = new String[] {"foo", "bar"};
        return Flux.fromArray(array);
    }

    private Flux<String> fooBarFromListFlux(){
        return Flux.fromIterable(Arrays.asList("foo","bar"));
    }

    private Flux<String> errorFlux(){
        return Flux.error(new RuntimeException());
    }

    private Flux<Long> counter(){
        return Flux.interval(Duration.ofMillis(1000L)).take(10);
    }

    private Mono<String> emptyMono(){
        return Mono.empty();
    }

    private Mono<String> monoWithNoSignal(){
        return Mono.never();
    }

    private Mono<String> fooMono(){
        return Mono.just("foo");
    }

    private Mono<String> errormono(){
        return Mono.error(new RuntimeException());
    }
}
