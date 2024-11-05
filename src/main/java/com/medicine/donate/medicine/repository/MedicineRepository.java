package com.medicine.donate.medicine.repository;

import com.medicine.donate.medicine.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {
    Optional<MedicineEntity> findByBarcode(String barcode);
}