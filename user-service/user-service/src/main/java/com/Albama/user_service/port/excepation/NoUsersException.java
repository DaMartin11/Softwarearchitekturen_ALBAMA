package com.Albama.user_service.port.excepation;

public class NoUsersException extends Exception{
    public NoUsersException() {
        super("There are no registered users.");
    }

}
