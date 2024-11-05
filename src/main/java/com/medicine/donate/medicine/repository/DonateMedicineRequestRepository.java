package com.medicine.donate.medicine.repository;

import com.medicine.donate.medicine.dto.DonateMedicineRequestDto;
import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.entity.DonateMedicineRequestEntity;
import com.medicine.donate.medicine.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DonateMedicineRequestRepository extends JpaRepository<DonateMedicineRequestEntity, Long> {
    Page<DonateMedicineRequestEntity> findAllByUserOrderByIdDesc(UserEntity user, Pageable pageable);
    Page<DonateMedicineRequestEntity> findAllByOrderByIdDesc(Pageable pageable);
    Optional<DonateMedicineRequestEntity> findByUuid(UUID uuid);
}
