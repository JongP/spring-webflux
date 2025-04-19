package org.jong.webflux.reative;

import org.jong.webflux.SandBox;
import org.jong.webflux.reative.components.JongPublisher;
import org.jong.webflux.reative.components.JongSubsciber;
import org.reactivestreams.Subscriber;
import org.springframework.stereotype.Component;

@Component
public class ReactiveSandBox implements SandBox {

    @Override
    public void start(){
        JongPublisher<Integer> publisher = new JongPublisher<>();

        Subscriber<Integer> subscriber = new JongSubsciber<>();


        publisher.subscribe(subscriber);

        publisher.publishEvent(1);
        publisher.publishEvent(2);

        publisher.complete();

    }
}
