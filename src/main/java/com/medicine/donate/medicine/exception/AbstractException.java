package com.medicine.donate.medicine.exception;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;

@Log4j2
public abstract class AbstractException  extends RuntimeException {
    private static final long serialVersionUID = 8121725435940351260L;
    protected final Enum errorCode;

    public AbstractException(Enum errorCode) {
        super(getMessage(errorCode));
        this.errorCode = errorCode;
    }

    public AbstractException(Enum errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    private static String getMessage(Enum errorCode) {
        try {
            Field field = errorCode.getClass().getDeclaredField("message");
            field.setAccessible(true);
            return (String)field.get(errorCode);
        } catch (IllegalAccessException | NoSuchFieldException var2) {
            ReflectiveOperationException e = var2;
            log.error("Exception not found a message in Enum {}", errorCode, e);
            return errorCode.name();
        }
    }

    public Enum getErrorCode() {
        return this.errorCode;
    }
}