package com.medicine.donate.medicine.service;

import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.PaginatedResponse;
import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;
import org.springframework.web.bind.annotation.PathVariable;

public interface AdminService {
    GenericResponse<DonateMedicineResponseDto> updateDonationRequest(String requestUuid, DonationRequestStatus status);
    GenericResponse<DonateMedicineResponseDto> getDonationRequest(String requestUuid);
    GenericResponse<PaginatedResponse<DonateMedicineResponseDto>> getDonationRequest(final int page, final int size);

}
