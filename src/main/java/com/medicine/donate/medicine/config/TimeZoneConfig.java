package com.medicine.donate.medicine.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import java.util.TimeZone;

@Configuration
public class TimeZoneConfig {

    @PostConstruct
    public void setTimeZone() {
        // Set the default time zone to UTC+3
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
    }
}