package com.medicine.donate.medicine.service;

import com.medicine.donate.medicine.dto.DonateMedicineRequestDto;
import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.PaginatedResponse;
import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;

import java.util.List;

public interface DonationService {

    GenericResponse<DonateMedicineResponseDto> makeDonationRequest(final String uuid, final DonateMedicineRequestDto donateMedicineRequestDto);
    GenericResponse<DonateMedicineResponseDto> getDonationRequest(final String uuid);
    GenericResponse<DonateMedicineResponseDto> updateDonationRequest(final String  requestUuid, DonationRequestStatus status);
    GenericResponse<PaginatedResponse<DonateMedicineResponseDto>> getDonationRequest(final String uuid, final int page, final int size);

}
