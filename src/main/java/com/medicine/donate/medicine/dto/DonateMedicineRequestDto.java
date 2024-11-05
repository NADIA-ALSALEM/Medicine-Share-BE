package com.medicine.donate.medicine.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonateMedicineRequestDto {

    private List<String> images;

    private String barcode;


    private LocalDate expiryDate;

    private SurveyDto survey;
}
