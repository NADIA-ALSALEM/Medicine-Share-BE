package com.medicine.donate.medicine.exception;


import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;

@Log4j2
public class BusinessException extends AbstractException {

    public BusinessException(Enum errorCode) {
        super(errorCode);
    }

    public BusinessException(Enum errorCode, String message) {
        super(errorCode, message);
    }

    public Enum getErrorCode() {
        return this.errorCode;
    }
}