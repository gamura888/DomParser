package com.highpots.DomParser.common;

public class AppException extends Exception {

    private String message;

    public AppException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
