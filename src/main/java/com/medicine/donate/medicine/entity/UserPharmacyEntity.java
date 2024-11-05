package com.medicine.donate.medicine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_pharmacy")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPharmacyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")  // Reference the 'id' of MedicineEntity
    private MedicineEntity medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")  // Reference the 'uuid' of UserEntity
    private UserEntity user;

}
