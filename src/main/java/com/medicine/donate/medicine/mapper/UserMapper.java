package com.medicine.donate.medicine.mapper;

import com.medicine.donate.medicine.dto.RegisterUserRequestDto;
import com.medicine.donate.medicine.dto.UserDto;
import com.medicine.donate.medicine.entity.UserEntity;

public interface UserMapper {
    UserEntity registerUserRequestDtoToEntity(RegisterUserRequestDto registerUserRequestDto);
    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);
}
