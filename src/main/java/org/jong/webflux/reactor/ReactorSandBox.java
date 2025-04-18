package org.jong.webflux.reactor;

import org.jong.webflux.SandBox;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Component
public class ReactorSandBox implements SandBox {




@Override
    public void start(){
        Mono<Integer> mono = Mono.just(1)
                .map(it -> it*2)
                .doOnNext(this::doOnNext);


        mono.subscribe(this::subscribe);

    }

    public void doOnNext(Integer num){
        System.out.println("doOnNext");
    }

    public void subscribe(Integer num){
        System.out.println("subscribe");
    }
}
