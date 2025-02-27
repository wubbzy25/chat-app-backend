package com.chat.backend.Controllers;

import com.chat.backend.DTO.ChatRequestDTO;
import com.chat.backend.DTO.MessageRequestDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSChatController {

    @MessageMapping("/chat/{chatId}")
    public MessageRequestDTO getMessage(@Payload MessageRequestDTO message, @DestinationVariable String chatId) {
        System.out.println(message);
        return message;
    }

}
