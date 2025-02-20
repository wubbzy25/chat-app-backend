package com.chat.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class Users {
    @Id
    private String id;
    private String name;
    private String email;
    private String picture;
}
