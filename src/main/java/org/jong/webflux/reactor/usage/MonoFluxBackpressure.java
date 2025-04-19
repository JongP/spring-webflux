package org.jong.webflux.reactor.usage;

import org.jong.webflux.SandBox;
import org.jong.webflux.reactor.usage.model.User;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Component
public class MonoFluxBackpressure implements SandBox {



    @Override
    public void start() {

    }

    StepVerifier requestAllExpectedFour(Flux<User> flux){
        return StepVerifier.create(flux)
                .expectNextCount(4)
                .expectComplete();
    }

    StepVerifier requestOneExpectKylerTehnRequestOneExpectJesse(Flux<User> flux){
        return StepVerifier.create(flux)
                .thenRequest(1).expectNext(User.SKYLER)
                .thenRequest(1).expectNext(User.JESSE)
                .thenCancel();
    }

}
