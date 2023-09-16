package com.project.shop.exception;

public class NotOrderedException extends RuntimeException{
    public NotOrderedException(String msg) {
        super(msg);
    }
}
