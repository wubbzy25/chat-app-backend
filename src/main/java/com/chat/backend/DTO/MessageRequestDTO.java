package com.chat.backend.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDTO {
    private String chatId;
    private String message;
    private String senderId;
    private String picture;
    private String time;
}
