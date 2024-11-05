package com.medicine.donate.medicine.dto;

import com.medicine.donate.medicine.dto.enums.Gender;
import com.medicine.donate.medicine.dto.enums.UserActivationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Gender gender;
    private UserActivationStatus status;
}