package com.medicine.donate.medicine.controller;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.LoginRequest;
import com.medicine.donate.medicine.dto.JwtResponse;
import com.medicine.donate.medicine.dto.RegisterUserRequestDto;
import com.medicine.donate.medicine.repository.UserRepository;
import com.medicine.donate.medicine.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<JwtResponse>> register(@RequestBody @Valid RegisterUserRequestDto request) {
        JwtResponse jwtResponse = userService.register(request);
        GenericResponse<JwtResponse> response = GenericResponse.<JwtResponse>builder()
                .body(jwtResponse)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<JwtResponse>> login(@RequestBody @Valid LoginRequest request) {
        JwtResponse jwtResponse = userService.login(request);
        GenericResponse<JwtResponse> response = GenericResponse.<JwtResponse>builder()
                .body(jwtResponse)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}