package com.Albama.user_service.port.controlle;


import com.Albama.user_service.corn.model.AuthenticationRequest;
import com.Albama.user_service.corn.model.AuthenticationResponse;
import com.Albama.user_service.corn.model.RegisterRequest;
import com.Albama.user_service.corn.service.imple.AuthenticationService;
import com.Albama.user_service.corn.service.interfaces.IAuthenticationService;
import com.Albama.user_service.port.excepation.InvalidEmailException;
import com.Albama.user_service.port.excepation.UserEmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationService service;

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws UserEmailAlreadyExistsException, InvalidEmailException {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }





}


