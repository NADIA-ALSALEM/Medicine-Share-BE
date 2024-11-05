package com.medicine.donate.medicine.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.donate.medicine.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, 
                         AuthenticationException authException) throws IOException {

        // Create an instance of GenericResponse
        GenericResponse<String> genericResponse = GenericResponse.<String>builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(Collections.singletonList("Unauthorized access - please login to continue"))
                .build();

        // Set response status and content type
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Write the GenericResponse to the response body
        objectMapper.writeValue(response.getOutputStream(), genericResponse);
    }
}