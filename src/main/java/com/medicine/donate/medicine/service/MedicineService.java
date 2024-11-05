package com.medicine.donate.medicine.service;

import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.MedicineDto;

public interface MedicineService {

    GenericResponse<MedicineDto> getMedicineInfo(String barcode);
}
