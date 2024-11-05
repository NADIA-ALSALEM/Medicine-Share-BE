package com.medicine.donate.medicine.dto;

import com.medicine.donate.medicine.entity.MedicineEntity;
import com.medicine.donate.medicine.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SurveyDto {

    private double temperature;
    private boolean humidityControlled;
    private boolean unopenedSealed;

}
