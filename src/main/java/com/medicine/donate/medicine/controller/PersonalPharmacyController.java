package com.medicine.donate.medicine.controller;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.service.PersonalPharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-pharmacy")
@RequiredArgsConstructor
public class PersonalPharmacyController {

    private final PersonalPharmacyService personalPharmacyService;

    @PostMapping("/{barcode}")
    public ResponseEntity<GenericResponse<String>> addMedicineToPharmacy(@AuthenticationPrincipal UserDetails userDetails,
                                                                        @PathVariable final String barcode) {
        GenericResponse<String> stringGenericResponse = personalPharmacyService.addMedicineToPersonalPharmacy(userDetails.getUsername(), barcode);
        return ResponseEntity.ok(stringGenericResponse);

    }
}
