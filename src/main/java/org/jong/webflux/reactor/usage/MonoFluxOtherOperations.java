package org.jong.webflux.reactor.usage;

import org.jong.webflux.SandBox;
import org.jong.webflux.reactor.usage.model.User;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonoFluxOtherOperations implements SandBox {
    @Override
    public void start() {
        Mono<User> mono = Mono.just(User.JESSE);
        User user = mono.block();

        Flux<User> users = Flux.just(User.JESSE);
        Iterable<User> userIterable = users.toIterable();
    }

    // TODO Create a Flux of user from Flux of username, firstname and lastname.
    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
        return Flux.zip(usernameFlux,firstnameFlux,lastnameFlux).map(data -> new User(data.getT1(), data.getT2(), data.getT3()));
    }

    // TODO Return the mono which returns its value faster
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.firstWithValue(mono1, mono2);
    }

    // TODO Return the flux which returns the first value faster
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return Flux.firstWithValue(flux1, flux2);
    }

    // TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
    Mono<Void> fluxCompletion(Flux<User> flux) {
        return flux.then();
    }

    // TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
    Mono<User> nullAwareUserToMono(User user) {
        return Mono.justOrEmpty(user);
    }

    // TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
    Mono<User> emptyToSkyler(Mono<User> mono) {
        return mono.switchIfEmpty(Mono.just(User.SAUL));
    }
}
