package org.jong.webflux.reative.compoents;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class JongPublisher<T> implements Publisher<T> {
    private final List<Subscriber<? super T>> subscribers = new ArrayList<>();


    @Override
    public void subscribe(Subscriber<? super T> s) {
        if(s!=null){
            subscribers.add(s);
            s.onSubscribe(new JongSubscription());
        }
    }

    public void publishEvent(T object){
        for(Subscriber<? super T> subscriber : subscribers){
            try{
                subscriber.onNext(object);
            } catch (Exception e){
                subscriber.onError(e);
            }
        }
    }

    public void complete(){
        subscribers.forEach(Subscriber::onComplete);
        subscribers.clear();;
    }
}
