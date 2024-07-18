package com.Albama.user_service.port.excepation;

public class InvalidEmailException extends  Exception{
    public InvalidEmailException() {
        super("Invalid E-Mail.");
    }
}
