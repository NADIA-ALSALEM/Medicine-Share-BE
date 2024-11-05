package com.medicine.donate.medicine.mapper;

import com.medicine.donate.medicine.dto.MedicineDto;
import com.medicine.donate.medicine.dto.MedicineResponse;
import com.medicine.donate.medicine.entity.MedicineEntity;

import java.util.List;

public interface MedicineMapper {

    MedicineDto toDto(MedicineEntity medicineEntity);
    MedicineEntity toEntity(MedicineDto medicineDto);
    List<MedicineDto> toDtoList(List<MedicineEntity> medicineEntities);
    List<MedicineEntity> toEntityList(List<MedicineDto> medicineDtos);

}
