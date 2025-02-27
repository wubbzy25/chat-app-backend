package com.chat.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "users")
public class Users {
    @Id
    private String idUser;
    private String name;
    private String email;
    private String picture;

    public Users() {
        this.idUser = UUID.randomUUID().toString();
    }
}
