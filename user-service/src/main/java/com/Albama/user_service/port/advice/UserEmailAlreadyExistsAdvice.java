package com.Albama.user_service.port.advice;

import com.Albama.user_service.port.excepation.UserEmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class UserEmailAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(value = UserEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userEmailAlreadyExistsHandler(UserEmailAlreadyExistsException exception){
        return exception.getMessage();
    }

}
