package com.medicine.donate.medicine.service;

import com.medicine.donate.medicine.dto.GenericResponse;


public interface PersonalPharmacyService {

    GenericResponse<String> addMedicineToPersonalPharmacy(String uuid,  String barcode);
}
