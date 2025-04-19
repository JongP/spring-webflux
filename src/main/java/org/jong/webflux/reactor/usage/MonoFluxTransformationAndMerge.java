package org.jong.webflux.reactor.usage;

import org.jong.webflux.SandBox;
import org.jong.webflux.reactor.usage.model.User;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonoFluxTransformationAndMerge implements SandBox {
    @Override
    public void start() {
        asyncCapitalizeMany(Flux.just(User.SKYLER,User.SAUL)).subscribe(System.out::println);
        mergeFluxWithInterleave(Flux.just(User.SKYLER,User.SKYLER,User.SKYLER,User.SKYLER,User.SKYLER),Flux.just(User.SAUL,User.SAUL,User.SAUL,User.SAUL))
                .subscribe(System.out::println);

    }

    private Mono<User> capitalizeOne(Mono<User> mono){
        return mono.map(user -> new User(user.getUsername().toUpperCase(), user.getFirstname().toUpperCase(), user.getLastname().toUpperCase()));
    }

    private Flux<User> capitalizeMany(Flux<User> flux){
        return flux.map(user -> new User(user.getUsername().toUpperCase(), user.getFirstname().toUpperCase(), user.getLastname().toUpperCase()));
    }

    private Flux<User> asyncCapitalizeMany(Flux<User> flux){
        return flux.flatMap(this::asyncCapitalizeUser);
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2){
        return flux1.mergeWith(flux2);
        //final class FluxMerge<T> extends Flux<T> implements SourceProducer<T> {
        //	final Publisher<? extends T>[] sources;
    }

    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2){
        return flux1.concatWith(flux2);
    }

    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2){
        return mono1.mergeWith(mono2);
    }

}
