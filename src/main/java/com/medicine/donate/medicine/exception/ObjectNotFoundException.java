package com.medicine.donate.medicine.exception;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;

@Log4j2
public class ObjectNotFoundException extends AbstractException {


    public ObjectNotFoundException(Enum errorCode) {
        super(errorCode);
    }

    public ObjectNotFoundException(Enum errorCode, String message) {
        super(errorCode, message);
    }
}
