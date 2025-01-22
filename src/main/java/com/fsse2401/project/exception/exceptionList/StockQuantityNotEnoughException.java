package com.fsse2401.project.exception.exceptionList;

public class StockQuantityNotEnoughException extends RuntimeException{
    public StockQuantityNotEnoughException(Integer stock, Integer quantity) {
        super(String.format("the request product quantity: %d exist the stock quantity: %d", quantity, stock));
    }
}
