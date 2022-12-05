package org.saberinteractive.task;

import org.saberinteractive.task.message.Message;
import org.saberinteractive.task.message.MessageType;
import org.saberinteractive.task.processor.MessageProcessorService;
import org.saberinteractive.task.subscriber.SimpleSubscriber;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;

public class Main {

    private static final int BUFFER_OVERFLOW = 20;

    public static void main(String[] args) throws InterruptedException {


        MessageProcessorService<Message> processorServiceA =
                new MessageProcessorService<>(MessageType.TYPE_A);

        MessageProcessorService<Message> processorServiceB =
                new MessageProcessorService<>(MessageType.TYPE_B);

        MessageProcessorService<Message> processorServiceC =
                new MessageProcessorService<>(MessageType.TYPE_C);

        SubmissionPublisher<Message> publisher = new SubmissionPublisher<>(ForkJoinPool.commonPool(), BUFFER_OVERFLOW);
        SimpleSubscriber<Message> subscriberA = new SimpleSubscriber<>();
        SimpleSubscriber<Message> subscriberB = new SimpleSubscriber<>();
        SimpleSubscriber<Message> subscriberC = new SimpleSubscriber<>();

        processorServiceA.subscribe(subscriberA);
        processorServiceB.subscribe(subscriberB);
        processorServiceC.subscribe(subscriberC);

        publisher.subscribe(processorServiceA);
        publisher.subscribe(processorServiceB);
        publisher.subscribe(processorServiceC);

        publisher.submit(new Message(MessageType.TYPE_A, "message1"));

        Thread.sleep(2000);

        publisher.close();
    }
}