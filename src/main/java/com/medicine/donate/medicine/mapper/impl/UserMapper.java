package com.medicine.donate.medicine.mapper.impl;

import com.medicine.donate.medicine.dto.UserDto;
import com.medicine.donate.medicine.entity.UserEntity;

public class UserMapper {

    // Method to convert UserEntity to UserDto
    public static UserDto toDto(UserEntity userEntity) {
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
    public static UserEntity toEntity(UserDto userDto) {
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