package com.chat.backend.Repositories;

import com.chat.backend.Models.Chats;
import com.chat.backend.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<Users, UUID> {
     Optional<Users> findByEmail(String email);
     List<Users> findUsersByNameContainingIgnoreCase(String query);

    Optional<Users> findByIdUser(String idUser);
}
