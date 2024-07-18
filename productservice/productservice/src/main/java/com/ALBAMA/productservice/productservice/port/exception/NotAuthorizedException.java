package com.ALBAMA.productservice.productservice.port.exception;

public class NotAuthorizedException extends Exception{
    public NotAuthorizedException() {
        super("Not authorized.");
    }

}
