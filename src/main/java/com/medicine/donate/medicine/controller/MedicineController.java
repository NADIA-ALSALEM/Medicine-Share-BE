package com.medicine.donate.medicine.controller;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.MedicineDto;
import com.medicine.donate.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/{barcode}")
    public ResponseEntity<GenericResponse<MedicineDto>> getMedicine(@PathVariable String barcode){
        GenericResponse<MedicineDto> medicineInfo = medicineService.getMedicineInfo(barcode);

        return ResponseEntity.ok(medicineInfo);
    }


}