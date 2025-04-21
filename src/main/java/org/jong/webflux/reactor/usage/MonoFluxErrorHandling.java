package org.jong.webflux.reactor.usage;

import org.jong.webflux.SandBox;
import org.jong.webflux.reactor.usage.model.User;
import org.springframework.stereotype.Component;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonoFluxErrorHandling implements SandBox {
    @Override
    public void start() {
        capitalizeMany(Flux.just(User.SAUL)).subscribe(System.out::println);
    }

    // TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return mono.onErrorReturn(User.SAUL);
    }

    // TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(ex -> Flux.just(User.SAUL, User.JESSE));
    }

    // TODO Implement a method that capitalizes each user of the incoming flux using the
    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> {
            try {
                return capitalizeUser(user);
            } catch (GetOutOfHereException ex ){
                throw Exceptions.propagate(ex);
            }
        });
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
    }
}
