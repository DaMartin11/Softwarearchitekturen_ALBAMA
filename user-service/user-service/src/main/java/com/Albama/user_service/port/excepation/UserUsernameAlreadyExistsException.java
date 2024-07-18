package com.Albama.user_service.port.excepation;

public class UserUsernameAlreadyExistsException extends Exception{
    public UserUsernameAlreadyExistsException(String username) {
        super("A user with the username " + username + " already exists.");
    }
}
