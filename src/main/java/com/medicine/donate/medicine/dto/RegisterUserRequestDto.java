package com.medicine.donate.medicine.dto;

import com.medicine.donate.medicine.dto.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterUserRequestDto {

    @NotEmpty
    @Length(min = 3, max = 30)
    private String firstName;

    @NotEmpty
    @Length(min = 3, max = 30)
    private String lastName;

    @Email
    private String email;

    @NotEmpty
    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Length(min = 8)
    private String password;

}
