package com.mybuddy.pay.Util;


public class MyUncheckedCustomException extends RuntimeException {

    public MyUncheckedCustomException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public MyUncheckedCustomException(String message) {
        super(message);
    }
}