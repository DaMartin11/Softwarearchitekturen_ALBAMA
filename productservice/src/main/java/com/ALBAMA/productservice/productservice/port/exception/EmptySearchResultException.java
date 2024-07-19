package com.ALBAMA.productservice.productservice.port.exception;

public class EmptySearchResultException extends Exception{
    public EmptySearchResultException () {
        super("Search result is empty.");
    }
}
