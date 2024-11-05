package com.medicine.donate.medicine.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CloudinaryConfig {

    private final PropertiesConfig propertiesConfig;
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", propertiesConfig.getCloudinaryCouldName(),
            "api_key", propertiesConfig.getCloudinaryApiKey(),
            "api_secret", propertiesConfig.getCloudinaryApiSecret()
        ));
    }
}
