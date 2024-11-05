package com.medicine.donate.medicine.dto;

import com.medicine.donate.medicine.dto.enums.MedicineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicineDto {
    private Long id;
    private String barcode;
    private String medicineName;
    private String description;
    private MedicineType medicineType;
    private LocalDate expiryDate;
}