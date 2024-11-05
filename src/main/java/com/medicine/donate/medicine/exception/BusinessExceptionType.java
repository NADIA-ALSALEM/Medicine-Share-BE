package com.medicine.donate.medicine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BusinessExceptionType {

    CODE_001("Medicine is expired"),
    CODE_002("Medicine must be stored between 15-25°C."),
    CODE_003(" Medicine must have more than 6 months of shelf life"),
    CODE_004(" Medicine not found"),
    CODE_005(" User not found"),
    CODE_006("Error while saving the new medicine to personal pharmacy"),
    CODE_007("Medicine is not exist on you pharmacy"),
    CODE_008("Error while retrieving user info."),
    CODE_009("Error while making donation request"),
    CODE_010("Error while saving request images"),
    CODE_011("Medicine must be stored away from sunlight and moisture."),
    CODE_012("Only unopened and sealed medicines can be donated."),
    CODE_013("Donation Request is not exist"),
    CODE_014("Error while updating donation request"),
    CODE_015("User is already in the same status"),
    CODE_016("Error while activating/inactivating user"),




    ;


    private final String message;

    public static BusinessException businessException(BusinessExceptionType type) {
        return new BusinessException(type, type.getMessage());
    }

    public BusinessException businessException() {
        return new BusinessException(this, this.getMessage());
    }
}