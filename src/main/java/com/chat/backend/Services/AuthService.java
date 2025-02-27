package com.chat.backend.Services;

import com.chat.backend.DTO.AuthRequestDTO;
import com.chat.backend.DTO.AuthResponseDTO;
import com.chat.backend.Models.Users;
import com.chat.backend.Repositories.UserRepository;
import com.chat.backend.Utils.DateFormatted;
import com.chat.backend.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final DateFormatted dateFormatted;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(UserRepository userRepository, DateFormatted dateFormatted, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.dateFormatted = dateFormatted;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponseDTO Login(AuthRequestDTO authRequestDTO, HttpServletRequest request)
    {
        Optional<Users> users = userRepository.findByEmail(authRequestDTO.getEmail());
        if (users.isEmpty()) {
            Users new_users = new Users();
            new_users.setEmail(authRequestDTO.getEmail());
            new_users.setName(authRequestDTO.getName());
            new_users.setPicture(authRequestDTO.getPicture());
            Users userSaved = userRepository.save(new_users);
            String token = jwtUtils.GenerateToken(authRequestDTO.getEmail(), userSaved.getIdUser(), authRequestDTO.getName(), authRequestDTO.getPicture());
            AuthResponseDTO response = GetAuthResponseDTO(token, request);
            return response;
        } else {
            Users user = users.get();
            String token = jwtUtils.GenerateToken(authRequestDTO.getEmail(), user.getIdUser(), authRequestDTO.getName(), authRequestDTO.getPicture());
            AuthResponseDTO response = GetAuthResponseDTO(token, request);
            return response;
        }
    }

    private AuthResponseDTO GetAuthResponseDTO(String token, HttpServletRequest request) {
        AuthResponseDTO response = new AuthResponseDTO();
        response.setTimeStamp(dateFormatted.getDate());
        response.setCode("P-200");
        response.setMessage("Logged in successfully");
        response.setToken(token);
        response.setUri(request.getRequestURI());
        return response;
    }
}
