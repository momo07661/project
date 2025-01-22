package com.fsse2401.project.exception.exceptionList;

public class MissingRequiredDataException extends RuntimeException{
    public MissingRequiredDataException() {
        super("Input Data not enough");
    }
}
