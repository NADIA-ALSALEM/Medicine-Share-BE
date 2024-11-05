package com.medicine.donate.medicine.service.impl;

import com.medicine.donate.medicine.dto.*;
import com.medicine.donate.medicine.dto.enums.UserActivationStatus;
import com.medicine.donate.medicine.entity.UserEntity;
import com.medicine.donate.medicine.exception.*;
import com.medicine.donate.medicine.mapper.UserMapper;
import com.medicine.donate.medicine.repository.UserRepository;
import com.medicine.donate.medicine.service.UserService;
import com.medicine.donate.medicine.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;


    @Override
    public JwtResponse register(RegisterUserRequestDto registerUserRequestDto) {
        checkIfUserExist(registerUserRequestDto.getEmail());

        UserEntity savedUser;
        try {
            savedUser = userMapper.registerUserRequestDtoToEntity(registerUserRequestDto);
            savedUser.setStatus(UserActivationStatus.ACTIVE);
            savedUser = userRepository.save(savedUser);
        } catch (Exception e) {
            throw new BadRequestException("Error while saving user info");
        }

        String token = jwtUtil.generateToken(savedUser.getUuid().toString());
        return new JwtResponse(token);
    }


    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid email or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new JwtResponse(token);
    }

    @Override
    public UserEntity getUserByUuid(String uuid) {
        return userRepository.findByUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_008));
    }

    @Transactional
    @Override
    public GenericResponse<UserDto> activeInActiveUser(String userUUid, UserActivationStatus status) {
        UserEntity userEntity = userRepository.findByUuid(UUID.fromString(userUUid))
                .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_008));
        if (userEntity.getStatus().equals(status)){
            throw new BusinessException(BusinessExceptionType.CODE_015);
        }
        userEntity.setStatus(status);
        UserEntity updated;
        try {
             updated = userRepository.save(userEntity);
        } catch (Exception e) {
            throw new InternalServerErrorException(BusinessExceptionType.CODE_016);
        }
        return GenericResponse.<UserDto>builder()
                .status(HttpStatus.OK.value())
                .body(userMapper.toDto(updated))
                .build();
    }

    @Override
    public GenericResponse<Long> count() {
        return GenericResponse.<Long>builder()
                .body(userRepository.countByStatus(UserActivationStatus.ACTIVE))
                .status(HttpStatus.OK.value())
                .build();
    }


    private void checkIfUserExist(String email) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new BadRequestException("User already exists");
        }
    }


}
