package com.medicine.donate.medicine.exception;

public class InternalServerErrorException extends AbstractException{
    public InternalServerErrorException(Enum errorCode) {
        super(errorCode);
    }

    public InternalServerErrorException(Enum errorCode, String message) {
        super(errorCode, message);
    }
}
