package com.chat.backend.Repositories;

import com.chat.backend.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<Users, UUID> {
}
