package com.medicine.donate.medicine.repository;

import com.medicine.donate.medicine.dto.enums.UserActivationStatus;
import com.medicine.donate.medicine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUuid(UUID uuid);
    Long countByStatus(UserActivationStatus status);

}