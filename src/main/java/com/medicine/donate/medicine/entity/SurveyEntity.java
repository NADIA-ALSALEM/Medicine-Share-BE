package com.medicine.donate.medicine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private boolean humidityControlled;
    private boolean unopenedSealed;


    private boolean eligibleForDonation;

}
