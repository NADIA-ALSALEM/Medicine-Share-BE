package com.medicine.donate.medicine.mapper.impl;

import com.medicine.donate.medicine.dto.SurveyDto;
import com.medicine.donate.medicine.entity.SurveyEntity;
import com.medicine.donate.medicine.mapper.SurveyMapper;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapperImpl implements SurveyMapper {
    public SurveyDto toDto(SurveyEntity entity) {
        if (entity == null) {
            return null;
        }

        return SurveyDto.builder()
                .temperature(entity.getTemperature())
                .humidityControlled(entity.isHumidityControlled())
                .unopenedSealed(entity.isUnopenedSealed())
                .build();
    }

    // Method to convert SurveyDto to SurveyEntity
    public SurveyEntity toEntity(SurveyDto dto) {
        if (dto == null) {
            return null;
        }

        return SurveyEntity.builder()
                .temperature(dto.getTemperature())
                .humidityControlled(dto.isHumidityControlled())
                .unopenedSealed(dto.isUnopenedSealed())
                .build();
    }
}
