package com.chat.backend.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExceptionResponseDTO implements Serializable {
    private String timestamp;
    private String code;
    private String message;
    private String exception;
    private String uri;
}
