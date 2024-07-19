package com.ALBAMA.productservice.productservice.port.advice;

import com.ALBAMA.productservice.productservice.port.exception.NoProductsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class NoProductsAdvice {
    @ResponseBody
    @ExceptionHandler(value = NoProductsException.class)
    @ResponseStatus(HttpStatus.OK) // makes more sense than 404, because the request still came through
    String productNotFoundHandler(NoProductsException exception){
        return exception.getMessage();
    }

}
