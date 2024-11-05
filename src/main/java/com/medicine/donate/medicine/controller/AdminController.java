package com.medicine.donate.medicine.controller;

import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.UserDto;
import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;
import com.medicine.donate.medicine.dto.enums.UserActivationStatus;
import com.medicine.donate.medicine.exception.BusinessExceptionType;
import com.medicine.donate.medicine.exception.InternalServerErrorException;
import com.medicine.donate.medicine.exception.ObjectNotFoundException;
import com.medicine.donate.medicine.service.DonationService;
import com.medicine.donate.medicine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DonationService donationService;
    private final UserService userService;

    @PutMapping("/donations/{requestUuid}/{status}")
    ResponseEntity<GenericResponse<DonateMedicineResponseDto>> updateDonationRequest(@PathVariable("requestUuid") String requestUuid,
                                                                                     @PathVariable("status") DonationRequestStatus status){
        return ResponseEntity.ok(donationService.updateDonationRequest(requestUuid, status));
    }
    @GetMapping("/donations/{uuid}")
    ResponseEntity<GenericResponse<DonateMedicineResponseDto>> getDonationRequest(@PathVariable("uuid") final String uuid){
        return ResponseEntity.ok(donationService.getDonationRequest(uuid));
    }

    @PutMapping("/users/{userUuid}/{status}")
    ResponseEntity<GenericResponse<UserDto>> activeInActiveUser(
            @PathVariable("userUuid") String userUuid,
            @PathVariable("status") UserActivationStatus status
    ){

        return ResponseEntity.ok(userService.activeInActiveUser(userUuid, status));
    }

}
