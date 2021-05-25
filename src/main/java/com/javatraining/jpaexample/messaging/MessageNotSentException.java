package com.javatraining.jpaexample.messaging;

public class MessageNotSentException extends RuntimeException {
    public MessageNotSentException() {
        super();
    }

    public MessageNotSentException(String message) {
        super(message);
    }
}
