package com.chat.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "messsages")
public class Messages {
    @Id
    private String idMessage;
    private String senderId;
    private String message;
    private String picture;
    private String time;
    private LocalDateTime created_At;

    public Messages() {
        this.idMessage = UUID.randomUUID().toString();
        this.created_At = LocalDateTime.now();

    }
}
