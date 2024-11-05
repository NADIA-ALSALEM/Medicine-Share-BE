package com.medicine.donate.medicine.controller;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.service.DonationService;
import com.medicine.donate.medicine.service.MedicineService;
import com.medicine.donate.medicine.service.UserService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statics")
@RequiredArgsConstructor
public class StaticsController {

    private final MedicineService medicineService ;
    private final DonationService donationService;
    private final UserService userService ;

    @GetMapping("/active-users")
    public ResponseEntity<GenericResponse<Long>> getUsersCount(){
        return ResponseEntity.ok(userService.count());
    }


}
