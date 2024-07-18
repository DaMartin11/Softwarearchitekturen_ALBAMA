package com.Albama.user_service.port.excepation;

public class UserEmailAlreadyExistsException extends Exception{
    public UserEmailAlreadyExistsException(String email) {
        super("A user with the email " + email + " already exists.");
    }
}
