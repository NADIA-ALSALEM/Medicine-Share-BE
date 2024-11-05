package com.medicine.donate.medicine.mapper;

import com.medicine.donate.medicine.dto.SurveyDto;
import com.medicine.donate.medicine.entity.SurveyEntity;

public interface SurveyMapper {
    SurveyDto toDto(SurveyEntity entity);
    SurveyEntity toEntity(SurveyDto dto);
}
