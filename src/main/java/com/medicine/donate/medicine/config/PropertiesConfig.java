package com.medicine.donate.medicine.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PropertiesConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry.time}")
    private int expiryTime;

    @Value("${cloudinary.cloud.name}")
    private String cloudinaryCouldName;

    @Value("${cloudinary.api.secret}")
    private String cloudinaryApiSecret;

    @Value("${cloudinary.api.key}")
    private String cloudinaryApiKey;

}
