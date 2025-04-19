package org.jong.webflux.reative.components;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class JongSubsciber<T> implements Subscriber<T> {
    private Subscription subscription;
    @Override
    public void onSubscribe(Subscription s) {
        this.subscription=s;
        s.request(2);
    }

    @Override
    public void onNext(T o) {
        System.out.println("subscriber onNext");
        System.out.println(o);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("subscriber onError");
        System.out.println(t.toString());
    }

    @Override
    public void onComplete() {
        System.out.println("subscriber onComplete");
    }

    public void cancelSubscription(){
        if(subscription!=null) {
            subscription.cancel();
        }
    }
}
