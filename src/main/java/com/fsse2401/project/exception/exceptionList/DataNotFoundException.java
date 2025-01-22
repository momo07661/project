package com.fsse2401.project.exception.exceptionList;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException() {
        super("Requested data not found");
    }
}
