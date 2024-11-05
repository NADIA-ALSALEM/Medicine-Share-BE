package com.medicine.donate.medicine.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.medicine.donate.medicine.dto.DonateMedicineRequestDto;
import com.medicine.donate.medicine.dto.DonateMedicineResponseDto;
import com.medicine.donate.medicine.dto.GenericResponse;
import com.medicine.donate.medicine.dto.PaginatedResponse;
import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;
import com.medicine.donate.medicine.entity.*;
import com.medicine.donate.medicine.exception.BusinessException;
import com.medicine.donate.medicine.exception.BusinessExceptionType;
import com.medicine.donate.medicine.exception.InternalServerErrorException;
import com.medicine.donate.medicine.exception.ObjectNotFoundException;
import com.medicine.donate.medicine.mapper.DonationRequestMapper;
import com.medicine.donate.medicine.mapper.SurveyMapper;
import com.medicine.donate.medicine.repository.*;
import com.medicine.donate.medicine.service.DonationService;

import com.medicine.donate.medicine.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DonationServiceImpl implements DonationService {

    private final SurveyMapper surveyMapper;
    private final UserMedicineRepository userMedicineRepository;
    private final UserService userService;
    private final DonationRequestMapper mapper;
    private final DonateMedicineRequestRepository donateMedicineRequestRepository;
    private final Cloudinary cloudinary;
    private final SurveyRepository surveyRepository;
    private final DonationRequestImagesRepository donationRequestImagesRepository;

    @Override
    public GenericResponse<DonateMedicineResponseDto> makeDonationRequest(final String uuid, final DonateMedicineRequestDto donateMedicineRequestDto) {

        UserPharmacyEntity userMedicineOpt =
                userMedicineRepository.findByUserUuidAndMedicineBarcode(UUID.fromString(uuid), donateMedicineRequestDto.getBarcode())
                        .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_007));

        UserEntity user = userService.getUserByUuid(uuid);

        MedicineEntity medicine = userMedicineOpt.getMedicine();

        // Check if expiry date is more than 6 months away
        if (medicine.getExpiryDate().isBefore(LocalDate.now().plusMonths(6))) {
            throw new BusinessException(BusinessExceptionType.CODE_003);
        }

        // Check user-provided conditions
        if (donateMedicineRequestDto.getSurvey().getTemperature() < 15 || donateMedicineRequestDto.getSurvey().getTemperature() > 25) {
            throw new BusinessException(BusinessExceptionType.CODE_002);
        }

        if (!donateMedicineRequestDto.getSurvey().isHumidityControlled()) {
            throw new BusinessException(BusinessExceptionType.CODE_011);
        }

        if (!donateMedicineRequestDto.getSurvey().isUnopenedSealed()) {
            throw new BusinessException(BusinessExceptionType.CODE_012);
        }

        SurveyEntity savedSurvey = surveyRepository.save(surveyMapper.toEntity(donateMedicineRequestDto.getSurvey()));
        DonateMedicineRequestEntity donateMedicineRequest = DonateMedicineRequestEntity
                .builder()
                .user(user)
                .status(DonationRequestStatus.CREATED)
                .medicine(medicine)
                .survey(savedSurvey)
                .build();

        DonateMedicineRequestEntity savedRequest;
        savedRequest = saveDonationRequest(donateMedicineRequest);

        List<DonationRequestImages> images = buildImagesRequestEntity(savedRequest.getUuid(), donateMedicineRequestDto.getImages());
        images = donationRequestImagesRepository.saveAll(images);
        savedRequest.setImages(images);
        savedRequest = saveDonationRequest(donateMedicineRequest);

        return GenericResponse.<DonateMedicineResponseDto>builder()
                .body(mapper.toResponse(savedRequest))
                .status(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public GenericResponse<DonateMedicineResponseDto> getDonationRequest(String uuid) {
        DonateMedicineRequestEntity donateMedicineRequestEntity = donateMedicineRequestRepository.findByUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_013));
        return GenericResponse.<DonateMedicineResponseDto>builder()
                .body(mapper.toResponse(donateMedicineRequestEntity))
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public GenericResponse<DonateMedicineResponseDto> updateDonationRequest(String requestUuid, DonationRequestStatus status) {
        DonateMedicineRequestEntity donateMedicineRequestEntity = donateMedicineRequestRepository.findByUuid(UUID.fromString(requestUuid))
                .orElseThrow(() -> new ObjectNotFoundException(BusinessExceptionType.CODE_013));
        donateMedicineRequestEntity.setStatus(status);
        DonateMedicineRequestEntity updated;
        try {
            updated = donateMedicineRequestRepository.save(donateMedicineRequestEntity);
            //TODO : send notification for customer in the new status for request

        } catch (Exception e) {
            throw new InternalServerErrorException(BusinessExceptionType.CODE_014);
        }
        return  GenericResponse.<DonateMedicineResponseDto>builder()
                .body(mapper.toResponse(updated))
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public GenericResponse<PaginatedResponse<DonateMedicineResponseDto>> getDonationRequest(String uuid, final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonateMedicineRequestEntity> all;
        if (Objects.isNull(uuid)) {
            all = donateMedicineRequestRepository.findAllByOrderByIdDesc(pageable);
        } else {
            UserEntity user = userService.getUserByUuid(uuid);
            all = donateMedicineRequestRepository.findAllByUserOrderByIdDesc(user, pageable);
        }

        return GenericResponse.<PaginatedResponse<DonateMedicineResponseDto>>builder()
                .status(HttpStatus.OK.value())
                .body(
                        PaginatedResponse.<DonateMedicineResponseDto>builder()
                                .total(all.getTotalPages())
                                .pageSize(size)
                                .page(page)
                                .data(mapper.toResponse(all.getContent().stream().toList()))
                                .build()
                )
                .build();
    }

    private DonateMedicineRequestEntity saveDonationRequest(DonateMedicineRequestEntity donateMedicineRequest) {

        DonateMedicineRequestEntity savedRequest;
        try {
            savedRequest = donateMedicineRequestRepository.save(donateMedicineRequest);
        } catch (Exception e) {
            throw new InternalServerErrorException(BusinessExceptionType.CODE_009);
        }
        return savedRequest;
    }


    // Updated method to upload Base64-encoded images to Cloudinary
    private List<DonationRequestImages> buildImagesRequestEntity(UUID uuid, List<String> base64Images) {
        List<DonationRequestImages> donationRequestImages = new ArrayList<>();

        try {
            // Iterate over each Base64-encoded image
            for (String base64Image : base64Images) {
                // Decode the Base64 string to get the image bytes
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                // Upload the decoded image bytes to Cloudinary
                Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.asMap(
                        "folder", "medicines/" + uuid.toString(), // Store images in a folder with UUID
                        "public_id", UUID.nameUUIDFromBytes(imageBytes).toString(),
                        "resource_type", "image"  // Specify the resource type
                ));

                // Extract the image URL from the upload result
                String imageUrl = uploadResult.get("secure_url").toString();

                // Create a new DonationRequestImages entity
                DonationRequestImages imageEntity = DonationRequestImages.builder()
                        .path(imageUrl)  // Store the image URL
                        .build();

                // Add it to the list
                donationRequestImages.add(imageEntity);
            }

        } catch (IOException e) {
            log.error("Error uploading Base64-encoded images to Cloudinary for donation request with UUID: {}", uuid, e);
            throw new InternalServerErrorException(BusinessExceptionType.CODE_009, "Unable to upload images");
        }

        return donationRequestImages;
    }

}
