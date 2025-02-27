package com.chat.backend.Controllers;

import com.chat.backend.DTO.AuthRequestDTO;
import com.chat.backend.DTO.AuthResponseDTO;
import com.chat.backend.Services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid  @RequestBody AuthRequestDTO authRequestDTO, HttpServletRequest request) {
        return new ResponseEntity<>(authService.Login(authRequestDTO, request), HttpStatus.OK);
    }
}
