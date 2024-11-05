package com.medicine.donate.medicine.mapper.impl;

import com.medicine.donate.medicine.dto.MedicineDto;
import com.medicine.donate.medicine.dto.MedicineResponse;
import com.medicine.donate.medicine.entity.MedicineEntity;
import com.medicine.donate.medicine.mapper.MedicineMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineMapperImpl implements MedicineMapper {

    @Override
    public  MedicineDto toDto(MedicineEntity medicineEntity) {
        if (medicineEntity == null) {
            return null;
        }

        return MedicineDto.builder()
                .id(medicineEntity.getId())
                .barcode(medicineEntity.getBarcode())
                .medicineName(medicineEntity.getMedicineName())
                .description(medicineEntity.getDescription())
                .medicineType(medicineEntity.getMedicineType())
                .expiryDate(medicineEntity.getExpiryDate())
                .build();
    }

    @Override
    public  MedicineEntity toEntity(MedicineDto medicineDto) {
        if (medicineDto == null) {
            return null;
        }

        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setId(medicineDto.getId());
        medicineEntity.setBarcode(medicineDto.getBarcode());
        medicineEntity.setMedicineName(medicineDto.getMedicineName());
        medicineEntity.setDescription(medicineDto.getDescription());
        medicineEntity.setMedicineType(medicineDto.getMedicineType());
        medicineEntity.setExpiryDate(medicineDto.getExpiryDate());

        return medicineEntity;
    }

    @Override
    public List<MedicineDto> toDtoList(List<MedicineEntity> medicineEntities) {
        if (medicineEntities == null) {
            return null;
        }

        return medicineEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public  List<MedicineEntity> toEntityList(List<MedicineDto> medicineDtos) {
        if (medicineDtos == null) {
            return null;
        }

        return medicineDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
