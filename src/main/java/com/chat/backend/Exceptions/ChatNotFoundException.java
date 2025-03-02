package com.chat.backend.Exceptions;

public class ChatNotFoundException extends RuntimeException{
    public ChatNotFoundException() {
        super("Chat Not Found");
    }
}
