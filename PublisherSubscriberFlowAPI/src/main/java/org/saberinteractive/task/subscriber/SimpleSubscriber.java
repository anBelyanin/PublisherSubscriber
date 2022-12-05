package org.saberinteractive.task.subscriber;

import org.saberinteractive.task.message.Message;

import java.util.concurrent.Flow;

public class SimpleSubscriber<T extends Message> implements Flow.Subscriber<T> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Message item) {
        System.out.println("Processed message body:" + item.toString() + ". With type: " + item.getMessageType());
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Subscriber done");
    }

}
