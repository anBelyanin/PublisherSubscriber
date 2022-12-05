package org.saberinteractive.task.subscriber;

import org.reactivestreams.Subscription;
import org.saberinteractive.task.message.Message;
import org.saberinteractive.task.message.MessageType;
import reactor.core.publisher.BaseSubscriber;

public class SimpleSubscriber<T extends Message> extends BaseSubscriber<Message> {

    private Subscription subscription;
    private MessageType messageType;

    public SimpleSubscriber(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    protected void hookOnNext(Message value) {
        if (this.messageType.equals(value.getMessageType())) {
            System.out.println("Processing message: " + value.toString());
        }
        super.request(1);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Subscriber done.");
    }

}
