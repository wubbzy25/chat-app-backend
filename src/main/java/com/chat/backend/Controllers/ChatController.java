package com.chat.backend.Controllers;

import com.chat.backend.DTO.ChatRequestDTO;
import com.chat.backend.DTO.ChatResponseDTO;
import com.chat.backend.Services.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/chats/")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private final ChatService chatService;
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;

    }

    @PostMapping("AddChat")
    public ResponseEntity<ChatResponseDTO> addChat(@RequestBody ChatRequestDTO chatRequestDTO, HttpServletRequest request) {
        return new ResponseEntity<>( chatService.createChat(chatRequestDTO, request), HttpStatus.OK);
    }
    @GetMapping("GetChats")
    public ResponseEntity<?> getChats(@RequestParam String idUser) {

        return new ResponseEntity<>(chatService.getChats(idUser), HttpStatus.OK);
    }

    @GetMapping("GetMessage")
    public String getMessage(@RequestParam UUID chatId) {
        return "Hello, this is a chat app!";
    }
    @PostMapping("PostMessage")
    public String postMessage(@RequestParam UUID chatId, @RequestParam String message) {
        return "Message posted successfully!";
    }

    @GetMapping("GetUsers")
    public ResponseEntity<?> getUsers(@RequestParam String idUser) {
        return new ResponseEntity<>(chatService.getUsers(idUser), HttpStatus.OK);
    }

    @GetMapping("GetUserInformation")
    public ResponseEntity<?> getUserInformation(@RequestParam String idUser) {
        return new ResponseEntity<>(chatService.GetUserInformation(idUser), HttpStatus.OK);
    }

    @GetMapping("GetMessages")
    public ResponseEntity<?> getMessages(@RequestParam String chatId ) {
        return new ResponseEntity<>(chatService.getMessages(chatId), HttpStatus.OK);
    }

    @GetMapping("GetLastMessage")
    public ResponseEntity<?> getLastMessage(@RequestParam String chatId) {
        return new ResponseEntity<>(chatService.GetLastMessage(chatId), HttpStatus.OK);
    }

    @GetMapping("SearchUsers")
    public ResponseEntity<?> getUser(@RequestParam String query, @RequestParam String idUser) {
        return new ResponseEntity<>(chatService.SearchUsers(query, idUser), HttpStatus.OK);
    }
}
