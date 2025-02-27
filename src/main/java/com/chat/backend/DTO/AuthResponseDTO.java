package com.chat.backend.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponseDTO {
 private String TimeStamp;
 private String code;
 private String message;
 private String token;
 private String uri;
}
