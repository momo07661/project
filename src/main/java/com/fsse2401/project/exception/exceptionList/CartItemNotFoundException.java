package com.fsse2401.project.exception.exceptionList;

public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException() {
        super("user requested cart item not found");
    }
}
