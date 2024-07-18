package com.Albama.user_service.corn.service.interfaces;

import com.Albama.user_service.corn.model.AuthenticationRequest;
import com.Albama.user_service.corn.model.AuthenticationResponse;
import com.Albama.user_service.corn.model.RegisterRequest;
import com.Albama.user_service.port.excepation.InvalidEmailException;
import com.Albama.user_service.port.excepation.UserEmailAlreadyExistsException;

public interface IAuthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public AuthenticationResponse register(RegisterRequest request) throws UserEmailAlreadyExistsException, InvalidEmailException;

}
