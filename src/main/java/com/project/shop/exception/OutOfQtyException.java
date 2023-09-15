package com.project.shop.exception;

public class OutOfQtyException extends RuntimeException {
    public OutOfQtyException(String msg) {
        super(msg);
    }
}
