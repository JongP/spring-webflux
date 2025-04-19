package org.jong.webflux.reactor;

import org.jong.webflux.SandBox;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class SimpleMonoCombination implements SandBox {




@Override
    public void start(){
        Mono<Integer> mono = Mono.just(1)   //MonoJust with value 1
                .map(this::doMap)    //MonoMapFuseable source: MonoJust mapper: doMap
                .doOnNext(this::doOnNext); //MonoPeekFuseable source: MonoMapFuseable


        mono.subscribe(this::subscribe);
        //LambdaMonoSubscriber

    }

    public Integer doMap(Integer input){
        return input*2;
    }

    public void doOnNext(Integer num){
        System.out.println("doOnNext");
    }

    public void subscribe(Integer num){
        System.out.println("subscribe");
    }
}
