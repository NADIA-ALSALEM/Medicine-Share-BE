package com.medicine.donate.medicine.controller;

import com.medicine.donate.medicine.dto.DonateMedicineRequestDto;
import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.PaginatedResponse;
import com.medicine.donate.medicine.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {


    private final DonationService donationService;

    @PostMapping("/")
    public ResponseEntity<GenericResponse<DonateMedicineResponseDto>> donateMedicine(@AuthenticationPrincipal UserDetails userDetails,
                                                                                     @Valid @RequestBody DonateMedicineRequestDto donateMedicineRequestDto) {
        GenericResponse<DonateMedicineResponseDto> response = donationService.makeDonationRequest(userDetails.getUsername(), donateMedicineRequestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<PaginatedResponse<DonateMedicineResponseDto>>> getDonationsRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false, defaultValue = "0")
            int page,
            @RequestParam(
                    required = false, defaultValue = "10"
            )
            int size
    ) {
        GenericResponse<PaginatedResponse<DonateMedicineResponseDto>> response = donationService.getDonationRequest(userDetails.getUsername(), page, size);
        return ResponseEntity.ok(response);
    }
}
