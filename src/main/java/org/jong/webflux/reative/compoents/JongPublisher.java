package org.jong.webflux.reative.compoents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class JongPublisher<T> implements Publisher<T> {
    private final List<JongSubscription<T>> subscriptions = new ArrayList<>();


    @Override
    public void subscribe(Subscriber<? super T> s) {
        if(s!=null){

            JongSubscription<T> subscription = new JongSubscription<>(this,s);

            subscriptions.add(subscription);
            s.onSubscribe(subscription);
        }
    }

    public void publishEvent(T object){
        for(JongSubscription<T> jongSubscription : subscriptions){
            if(jongSubscription.getRequested()>0) {
                jongSubscription.getSubsciber().onNext(object);
                jongSubscription.decrementRequested();
            }
        }
    }

    public void complete(){
        for(JongSubscription<T> jongSubscription : subscriptions){
            jongSubscription.getSubsciber().onComplete();
        }
        subscriptions.clear();

    }

    public void cancelSubscription(JongSubscription<T> subscription){
        subscriptions.remove(subscription);
    }
}
