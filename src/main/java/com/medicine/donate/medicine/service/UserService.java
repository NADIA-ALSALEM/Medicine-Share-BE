package com.medicine.donate.medicine.service;


import com.medicine.donate.medicine.dto.*;
import com.medicine.donate.medicine.dto.enums.UserActivationStatus;
import com.medicine.donate.medicine.entity.UserEntity;

import java.util.UUID;

public interface UserService {


    JwtResponse register(RegisterUserRequestDto registerUserRequestDto);

    JwtResponse login(LoginRequest request);

    UserEntity getUserByUuid(String uuid);

    GenericResponse<UserDto> activeInActiveUser(String userUUid, UserActivationStatus status);

    GenericResponse<Long> count();
}
