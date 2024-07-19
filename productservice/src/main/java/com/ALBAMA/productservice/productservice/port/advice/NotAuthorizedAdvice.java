package com.ALBAMA.productservice.productservice.port.advice;

import com.ALBAMA.productservice.productservice.port.exception.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class NotAuthorizedAdvice {

    @ResponseBody
    @ExceptionHandler(value = NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String notAuthorizedHandler(NotAuthorizedException exception){
        return exception.getMessage();
    }

}
