package org.saberinteractive.task.processor;

import org.saberinteractive.task.message.Message;
import org.saberinteractive.task.message.MessageType;

import java.util.concurrent.Flow;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Predicate;

public class MessageProcessorService<T extends Message>
        extends SubmissionPublisher<Message>
        implements Flow.Processor<Message, Message> {

    private final static int BUFFER_OVERFLOW = 20;

    private Flow.Subscription subscription;
    private Predicate<Message> predicate;
    private MessageType messageType;

    public MessageProcessorService(MessageType messageType) {
        super(ForkJoinPool.commonPool(), BUFFER_OVERFLOW);
        this.predicate = m -> m.getMessageType().equals(messageType);
        this.messageType = messageType;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Message item) {
        if (predicate.test(item)) {
            this.submit(item);
        }
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Message processor completed");
    }
}