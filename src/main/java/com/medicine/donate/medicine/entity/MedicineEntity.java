package com.medicine.donate.medicine.entity;

import com.medicine.donate.medicine.dto.enums.MedicineType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "medicine")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key of the medicine table

    @Column(unique = true)
    private String barcode;  // Unique barcode for the medicine

    private String medicineName;
    private String description;

    @Enumerated(EnumType.STRING)
    private MedicineType medicineType;

    private LocalDate expiryDate;

    @OneToMany(mappedBy = "medicine") // Reference to the UserPharmacyEntity through the 'medicine' field
    private List<UserPharmacyEntity> users;

}
