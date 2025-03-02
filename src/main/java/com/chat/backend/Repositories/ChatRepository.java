package com.chat.backend.Repositories;

import com.chat.backend.Models.Chats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends MongoRepository<Chats,UUID> {
    List<Chats> findByParticipantsContaining(String IdUser);
   Optional<Chats> findByIdChat(String idChat);
}
