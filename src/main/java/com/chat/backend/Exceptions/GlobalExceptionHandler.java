package com.chat.backend.Exceptions;

import com.chat.backend.DTO.ExceptionResponseDTO;
import com.chat.backend.Utils.DateFormatted;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final DateFormatted dateFormatted;

    @Autowired
    public GlobalExceptionHandler(DateFormatted dateFormatted){
        this.dateFormatted = dateFormatted;
    }

    @ExceptionHandler(JwtTokenExpirationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleJwtTokenExpirationException(JwtTokenExpirationException ex, HttpServletRequest request) {

        return new ResponseEntity<>(getExceptionResponseDTO("400", "the token is invalid or expired", "JwtTokenExpiration", request),HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(JwtTokenMissingException.class)
    public ResponseEntity<ExceptionResponseDTO> handleJwtTokenMissingException(JwtTokenMissingException ex, HttpServletRequest request) {

        return new ResponseEntity<>(getExceptionResponseDTO("401", "unauthorized, missing token", "JwtTokenMissing", request), HttpStatus.UNAUTHORIZED) ;
    }

    @ExceptionHandler(ChatNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleChatNotFoundException(ChatNotFoundException ex, HttpServletRequest request) {

        return new ResponseEntity<>(getExceptionResponseDTO("404", "chat not found", "ChatNotFoundException", request), HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(getExceptionResponseDTO("404", "user not found", "UserNotFoundException", request), HttpStatus.NOT_FOUND) ;
    }

    private ExceptionResponseDTO getExceptionResponseDTO(String code, String message, String exception, HttpServletRequest request){
        ExceptionResponseDTO response = new ExceptionResponseDTO();
        response.setTimestamp(dateFormatted.getDate());
        response.setCode(code);
        response.setMessage(message);
        response.setException(exception);
        response.setUri(request.getRequestURI());
        return response;
    }
}
