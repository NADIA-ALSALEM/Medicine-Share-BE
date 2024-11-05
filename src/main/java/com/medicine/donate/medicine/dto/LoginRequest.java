package com.medicine.donate.medicine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 8)
    private String password;
}
