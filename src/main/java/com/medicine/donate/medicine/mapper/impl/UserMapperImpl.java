package com.medicine.donate.medicine.mapper.impl;

import com.medicine.donate.medicine.dto.RegisterUserRequestDto;
import com.medicine.donate.medicine.dto.UserDto;
import com.medicine.donate.medicine.entity.UserEntity;
import com.medicine.donate.medicine.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUserRequestDtoToEntity(RegisterUserRequestDto registerUserRequestDto) {
        return UserEntity.builder()
                .phone(registerUserRequestDto.getPhone())
                .email(registerUserRequestDto.getEmail())
                .password(registerUserRequestDto.getPassword())
                .address(registerUserRequestDto.getAddress())
                .firstName(registerUserRequestDto.getFirstName())
                .lastName(registerUserRequestDto.getLastName())
                .gender(registerUserRequestDto.getGender())
                .password(passwordEncoder.encode(registerUserRequestDto.getPassword()))
                .build();
    }
    // Method to convert UserEntity to UserDto
    @Override
    public  UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return UserDto.builder()
                .uuid(userEntity.getUuid())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .address(userEntity.getAddress())
                .gender(userEntity.getGender())
                .status(userEntity.getStatus())
                .build();
    }

    // Method to convert UserDto to UserEntity
    @Override
    public  UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return UserEntity.builder()
                .uuid(userDto.getUuid())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .gender(userDto.getGender())
                .status(userDto.getStatus())
                .build();
    }
}
