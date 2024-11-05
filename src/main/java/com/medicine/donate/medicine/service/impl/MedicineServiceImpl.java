package com.medicine.donate.medicine.service.impl;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.MedicineDto;
import com.medicine.donate.medicine.entity.MedicineEntity;
import com.medicine.donate.medicine.exception.BusinessException;
import com.medicine.donate.medicine.exception.BusinessExceptionType;
import com.medicine.donate.medicine.exception.ObjectNotFoundException;
import com.medicine.donate.medicine.mapper.MedicineMapper;
import com.medicine.donate.medicine.repository.MedicineRepository;
import com.medicine.donate.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    public GenericResponse<MedicineDto> getMedicineInfo(String barcode) {

        MedicineEntity medicine = medicineRepository.findByBarcode(barcode)
                .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_004));

        boolean isExpired = medicine.getExpiryDate().isBefore(LocalDate.now());
        if (isExpired) {
            throw new BusinessException(BusinessExceptionType.CODE_001);
        }

        return GenericResponse.<MedicineDto>builder()
                .body(medicineMapper.toDto(medicine))
                .status(HttpStatus.OK.value())
                .build();
    }
}