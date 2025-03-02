package com.chat.backend.Controllers;

import com.chat.backend.DTO.ChatRequestDTO;
import com.chat.backend.DTO.MessageRequestDTO;
import com.chat.backend.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat/{chatId}")
    public MessageRequestDTO getMessage(@Payload MessageRequestDTO message, @DestinationVariable String chatId) {
        chatService.SaveMessage(chatId, message);
        return message;
    }

}
