package org.jong.webflux.reactor.usage;


import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class MonoFluxInstantiationTest {



    @Test
    void expectFooBarComplete(){
        Flux test = Flux.just("foo","bar");

        StepVerifier.create(test)
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();

    }


    void expectedFooBarError(Flux<String> test){
        StepVerifier.create(test)
                    .expectNext("foo")
                    .expectNext("bar")
                    .verifyError(RuntimeException.class);

    }

    void expect10Elemenent(Flux<String> test){
        StepVerifier.create(test)
                .thenAwait(Duration.ofMinutes(3))
                .expectNextCount(10)
                .verifyComplete();
    }

}