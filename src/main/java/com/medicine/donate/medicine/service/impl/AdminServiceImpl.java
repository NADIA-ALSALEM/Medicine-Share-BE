package com.medicine.donate.medicine.service.impl;

import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.PaginatedResponse;
import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;
import com.medicine.donate.medicine.service.AdminService;
import com.medicine.donate.medicine.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final DonationService donationService;

    @Override
    public GenericResponse<DonateMedicineResponseDto> updateDonationRequest(String requestUuid, DonationRequestStatus status) {
        return null;
    }

    @Override
    public GenericResponse<DonateMedicineResponseDto> getDonationRequest(String requestUuid) {
        return null;
    }

    @Override
    public GenericResponse<PaginatedResponse<DonateMedicineResponseDto>> getDonationRequest(int page, int size) {
        return donationService.getDonationRequest(null, page, size);
    }
}
