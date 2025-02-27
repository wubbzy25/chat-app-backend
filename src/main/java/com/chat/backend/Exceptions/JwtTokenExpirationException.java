package com.chat.backend.Exceptions;

public class JwtTokenExpirationException extends RuntimeException{
 public JwtTokenExpirationException() {
     super("Jwt token has expired");
 }
}
