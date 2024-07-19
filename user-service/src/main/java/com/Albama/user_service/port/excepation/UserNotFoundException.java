package com.Albama.user_service.port.excepation;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User not found");
    }

}
