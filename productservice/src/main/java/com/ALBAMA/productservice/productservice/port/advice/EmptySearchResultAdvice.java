package com.ALBAMA.productservice.productservice.port.advice;

import com.ALBAMA.productservice.productservice.port.exception.EmptySearchResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmptySearchResultAdvice {
    @ResponseBody
    @ExceptionHandler(value = EmptySearchResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(EmptySearchResultException exception){
        return exception.getMessage();
    }

}
