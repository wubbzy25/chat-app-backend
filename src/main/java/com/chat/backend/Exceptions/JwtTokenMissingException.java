package com.chat.backend.Exceptions;

public class JwtTokenMissingException extends RuntimeException{
  public JwtTokenMissingException() {
    super("JWT token is missing");
  }
}
