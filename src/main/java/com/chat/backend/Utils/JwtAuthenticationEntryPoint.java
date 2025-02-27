package com.chat.backend.Utils;

import com.chat.backend.DTO.ExceptionResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final DateFormatted dateFormatted;

    public JwtAuthenticationEntryPoint(DateFormatted dateFormatted) {
        this.dateFormatted = dateFormatted;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO();
        responseDTO.setTimestamp(dateFormatted.getDate());
        responseDTO.setCode("P-401");
        responseDTO.setException("authException");
        responseDTO.setMessage("Access Denied: You are not logged in. Please log in to access this URL. If you believe this is an error, contact your administrator.");
        responseDTO.setUri(request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDTO));
    }
}
