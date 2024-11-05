package com.medicine.donate.medicine.mapper.impl;

import com.medicine.donate.medicine.config.PropertiesConfig;
import com.medicine.donate.medicine.dto.DonateMedicineRequestDto;
import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.entity.DonateMedicineRequestEntity;
import com.medicine.donate.medicine.entity.DonationRequestImages;
import com.medicine.donate.medicine.mapper.DonationRequestMapper;
import com.medicine.donate.medicine.mapper.MedicineMapper;
import com.medicine.donate.medicine.mapper.SurveyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DonationRequestMapperImpl implements DonationRequestMapper {

    private final SurveyMapper surveyMapper;
    private final PropertiesConfig propertiesConfig;
    private final MedicineMapper medicineMapper;

    @Override
    public DonateMedicineResponseDto toResponse(DonateMedicineRequestEntity donateMedicineRequestEntity) {

        return DonateMedicineResponseDto.builder()
                .id(donateMedicineRequestEntity.getId())
                .uuid(donateMedicineRequestEntity.getUuid())
                .survey(surveyMapper.toDto(donateMedicineRequestEntity.getSurvey()))
                .creationDate(donateMedicineRequestEntity.getCreationDate())
                .images(buildImagesUrl(donateMedicineRequestEntity.getImages()))
                .maker(donateMedicineRequestEntity.getUser().getUuid().toString())
                .medicine(medicineMapper.toDto(donateMedicineRequestEntity.getMedicine()))
                .build();


    }

    @Override
    public List<DonateMedicineResponseDto> toResponse(List<DonateMedicineRequestEntity> donateMedicineRequestDto) {
        List<DonateMedicineResponseDto> responseDtos = new ArrayList<>();
        for (DonateMedicineRequestEntity donateMedicineRequestEntity : donateMedicineRequestDto) {
            responseDtos.add(toResponse(donateMedicineRequestEntity));
        }
        return responseDtos;
    }

    private List<String> buildImagesUrl(List<DonationRequestImages> images) {

        return images.stream()
                .map(DonationRequestImages::getPath)  // Construct the full URL
                .collect(Collectors.toList());  // Collect the URLs into a list
    }
}
