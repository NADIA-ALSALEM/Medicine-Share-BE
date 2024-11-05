package com.medicine.donate.medicine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineResponse {

    private String message;
    private MedicineDto medicine;

}


