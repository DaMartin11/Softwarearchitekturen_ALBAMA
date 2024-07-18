package com.ALBAMA.cart_service.cartservice.port.cart.advice;


import com.ALBAMA.cart_service.cartservice.port.cart.exception.CartNotFoundException;
import com.ALBAMA.cart_service.cartservice.port.cart.exception.ErrorCreatingCartException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorCreatingCartAdvice {
    @ResponseBody
    @ExceptionHandler(value = ErrorCreatingCartException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String cartNotFoundHandler(CartNotFoundException exception) {
        return exception.getMessage();
    }
}

