package com.medicine.donate.medicine.mapper;

import com.medicine.donate.medicine.dto.DonateMedicineRequestDto;
import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.entity.DonateMedicineRequestEntity;

import java.util.List;

public interface DonationRequestMapper {

    DonateMedicineResponseDto toResponse(DonateMedicineRequestEntity donateMedicineRequestDto);
    List<DonateMedicineResponseDto> toResponse(List<DonateMedicineRequestEntity> donateMedicineRequestDto);
}
