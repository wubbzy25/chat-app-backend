package com.chat.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "messsages")
public class Messages {
    @Id
    private String idMessage;
    private String sender;
    private String content;
    private String timestamp;

    public Messages() {
        this.idMessage = UUID.randomUUID().toString();
    }
}
