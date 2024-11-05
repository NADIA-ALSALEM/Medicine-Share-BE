package com.medicine.donate.medicine.repository;

import com.medicine.donate.medicine.entity.DonationRequestImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRequestImagesRepository extends JpaRepository<DonationRequestImages, Long> {
}
