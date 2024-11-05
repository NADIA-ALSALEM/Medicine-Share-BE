package com.medicine.donate.medicine.service.impl;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.entity.MedicineEntity;
import com.medicine.donate.medicine.entity.UserEntity;
import com.medicine.donate.medicine.entity.UserPharmacyEntity;
import com.medicine.donate.medicine.exception.BusinessException;
import com.medicine.donate.medicine.exception.BusinessExceptionType;
import com.medicine.donate.medicine.exception.InternalServerErrorException;
import com.medicine.donate.medicine.exception.ObjectNotFoundException;
import com.medicine.donate.medicine.repository.MedicineRepository;
import com.medicine.donate.medicine.repository.UserMedicineRepository;
import com.medicine.donate.medicine.repository.UserRepository;
import com.medicine.donate.medicine.service.PersonalPharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalPharmacyServiceImpl implements PersonalPharmacyService {

    private final MedicineRepository medicineRepository;
    private final UserMedicineRepository userMedicineRepository;
    private final UserRepository userRepository;

    @Override
    public GenericResponse<String> addMedicineToPersonalPharmacy(final String uuid, final String barcode) {
        // Find the user by phone number

        // Find the medicine by barcode
        MedicineEntity medicine = medicineRepository.findByBarcode(barcode)
                .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_004));

        // Check if the medicine is expired
        if (medicine.getExpiryDate().isBefore(LocalDate.now())) {
            throw new BusinessException(BusinessExceptionType.CODE_001);
        }

        UserEntity userEntity = userRepository.findByUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.CODE_005));

        // Add the medicine to the user's personal pharmacy
        UserPharmacyEntity userMedicine = UserPharmacyEntity
                .builder()
                .user(userEntity)
                .medicine(medicine)
                .build();

        try {
            userMedicineRepository.save(userMedicine);
        } catch (Exception exception) {
            throw new InternalServerErrorException(BusinessExceptionType.CODE_006);
        }

        return GenericResponse.<String>builder()
                .body("Medicine added to personal pharmacy")
                .status(HttpStatus.OK.value())
                .build();
    }
}
