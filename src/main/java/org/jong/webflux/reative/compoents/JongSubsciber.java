package org.jong.webflux.reative.compoents;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class JongSubsciber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T o) {
        System.out.println("subscriber onNext");
        System.out.println(o);
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
}
