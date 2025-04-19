package org.jong.webflux.reative.components;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.Getter;

@Getter
public class JongSubscription<T> implements Subscription {
    private final JongPublisher<T> publisher;
    private final Subscriber<? super T> subsciber;
    private long requested = 0L;

    public JongSubscription(JongPublisher<T> publisher, Subscriber<? super T> subscriber) {
        this.publisher = publisher;
        this.subsciber = subscriber;
    }

    @Override
    public void request(long n) {
        if(n<=0){
            throw new IllegalArgumentException();
        }

        requested+=n;
    }

    @Override
    public void cancel() {
        publisher.cancelSubscription(this);
    }

    public void decrementRequested(){
        if(requested>0){
            requested-=1;
        }
    }

}
