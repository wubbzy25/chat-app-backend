package com.chat.backend.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthRequestDTO {
    @NotEmpty(message = "the name can't be empty")
    private String name;
    @NotEmpty(message = "the email can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com|outlook\\.com|hotmail\\.com|icloud\\.com)$", message = "The email dont is valid")
    private String email;
    @NotEmpty(message = "the picture can't be empty")
    private String picture;
}
