package com.medicine.donate.medicine.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "donation_request_images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DonationRequestImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String path;

}
