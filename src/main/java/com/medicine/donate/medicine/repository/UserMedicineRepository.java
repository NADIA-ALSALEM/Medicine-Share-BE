package com.medicine.donate.medicine.repository;

import com.medicine.donate.medicine.entity.UserPharmacyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserMedicineRepository extends JpaRepository<UserPharmacyEntity, Long> {
    Optional<UserPharmacyEntity> findByUserUuidAndMedicineBarcode(UUID userId, String barcode);
}
