package org.saberinteractive.task.message;

public class Message {

    private MessageType messageType;

    private String messageBody;

    public Message(MessageType messageType, String messageBody) {
        this.messageType = messageType;
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return this.getMessageBody();
    }
}
