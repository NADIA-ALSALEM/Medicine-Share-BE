package com.medicine.donate.medicine.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}