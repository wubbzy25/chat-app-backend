package com.chat.backend.Services;

import com.chat.backend.DTO.ChatRequestDTO;
import com.chat.backend.DTO.ChatResponseDTO;
import com.chat.backend.Models.Chats;
import com.chat.backend.Models.Users;
import com.chat.backend.Repositories.ChatRepository;
import com.chat.backend.Repositories.UserRepository;
import com.chat.backend.Utils.DateFormatted;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final DateFormatted dateFormatted;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserRepository userRepository, DateFormatted dateFormatted) {
    this.chatRepository = chatRepository;
    this.userRepository = userRepository;
    this.dateFormatted = dateFormatted;
    }
    public List<Chats> getChats(String IdUser) {
        List<Chats> chats = chatRepository.findByParticipantsContaining(IdUser);
        return chats.stream()
                .peek(chat -> chat.setParticipants(
                        chat.getParticipants().stream()
                                .filter(participant -> !participant.equals(IdUser))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public List<Users> getUsers(String idUser) {

        List<Users> Users = userRepository.findAll();
        Collections.shuffle(Users);
        return Users.stream().limit(5).filter(user -> !user.getIdUser().equals(idUser)).collect(Collectors.toList());}

    public List<Users> SearchUsers(String query, String idUser){
        List<Users> Users =  userRepository.findUsersByNameContainingIgnoreCase(query);
        return Users.stream().filter(user -> !user.getIdUser().equals(idUser)).collect(Collectors.toList());
    }


    public Users GetUserInformation(String idUser) {
      return userRepository.findByIdUser(idUser).orElse(null);
    }

    public ChatResponseDTO createChat(ChatRequestDTO chatRequestDTO, HttpServletRequest request) {
        Chats chat = new Chats();
        chat.setParticipants(List.of(chatRequestDTO.getIdUser1(), chatRequestDTO.getIdUser2()));
        chatRepository.save(chat);
        ChatResponseDTO response = new ChatResponseDTO();
        response.setTimestamp(dateFormatted.getDate());
        response.setCode("P-200");
        response.setMessage("Chat created successfully");
        response.setUri(request.getRequestURI());
        return response;
    }
}


