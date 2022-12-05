package org.saberinteractive.task;

import org.saberinteractive.task.message.Message;
import org.saberinteractive.task.message.MessageType;
import org.saberinteractive.task.subscriber.SimpleSubscriber;
import reactor.core.publisher.*;

import java.time.Duration;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Message> messagesList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i < 11) {
                messagesList.add(new Message(MessageType.TYPE_A, UUID.randomUUID().toString()));
            }
            messagesList.add(new Message(MessageType.TYPE_B, UUID.randomUUID().toString()));
        }

        SimpleSubscriber<Message> subscriberA = new SimpleSubscriber<>(MessageType.TYPE_A);
        SimpleSubscriber<Message> subscriberC = new SimpleSubscriber<>(MessageType.TYPE_C);
        SimpleSubscriber<Message> subscriberB = new SimpleSubscriber<>(MessageType.TYPE_B);

        Flux.fromStream(messagesList.stream()
                .filter(m -> m.getMessageType().equals(MessageType.TYPE_A)))
                .delayElements(Duration.ofMillis(200))
                .subscribe(subscriberA);

        Flux.fromStream(messagesList.stream()
                .filter(m -> m.getMessageType().equals(MessageType.TYPE_B)))
                .subscribe(subscriberB);

        Flux.fromStream(messagesList.stream()
                        .filter(m -> m.getMessageType().equals(MessageType.TYPE_C)))
                .subscribe(subscriberC);

        Thread.sleep(5000);
    }
}