package org.saberinteractive.task.message;


public class Message {

    private MessageType messageType;
    private String messageBody;

    public Message(MessageType messageType, String messageBody) {
        this.messageBody = messageBody;
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    @Override
    public String toString() {
        return "{ Body: " + this.messageBody + ". With type: " + this.messageType + " }";
    }
}
