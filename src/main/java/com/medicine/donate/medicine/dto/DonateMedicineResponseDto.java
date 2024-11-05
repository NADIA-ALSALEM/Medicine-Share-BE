package com.medicine.donate.medicine.dto;

import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;
import com.medicine.donate.medicine.entity.MedicineEntity;
import com.medicine.donate.medicine.entity.SurveyEntity;
import com.medicine.donate.medicine.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class DonateMedicineResponseDto {
    private Long id;
    private List<String> images;
    private LocalDateTime creationDate;
    private DonationRequestStatus status;
    private UUID uuid;
    private String maker ;
    private MedicineDto medicine ;
    private SurveyDto survey;
}
