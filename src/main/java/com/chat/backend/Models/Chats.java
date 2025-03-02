package com.chat.backend.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "chats")
public class Chats {
    private String idChat;
    private List<String> participants;
    private List<Messages> messages;

    public Chats() {
        this.idChat = UUID.randomUUID().toString();
        this.participants = List.of();
        this.messages = List.of();
    }
}
