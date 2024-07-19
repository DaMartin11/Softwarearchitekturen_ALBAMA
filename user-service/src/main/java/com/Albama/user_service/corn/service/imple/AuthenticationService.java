package com.Albama.user_service.corn.service.imple;

import com.Albama.user_service.corn.model.*;
import com.Albama.user_service.corn.service.interfaces.IAuthenticationService;
import com.Albama.user_service.corn.service.interfaces.UserRepository;
import com.Albama.user_service.port.excepation.InvalidEmailException;
import com.Albama.user_service.port.excepation.UserEmailAlreadyExistsException;
import com.Albama.user_service.port.excepation.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @SneakyThrows
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request)  {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // user is correct when here
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException());

        // generate Token
        var jwtToken = jwtService.generateToken(user.getClaims(), user);
        // send Token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .isAdmin(user.getRole() == Role.ADMIN)
                .build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws UserEmailAlreadyExistsException, InvalidEmailException {
        String email = request.getEmail();
        if (!repository.findByEmail(email).isEmpty()) {
            throw new UserEmailAlreadyExistsException(email);
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailException();
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .country(request.getCountry())
                .city(request.getCity())
                .zipCode(request.getZipCode())
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user.getClaims(), user); // no extra claims

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .isAdmin(user.getRole() == Role.ADMIN)
                .build();
    }

    private boolean isValidEmail(String email) {
        String regex = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        return email.matches(regex);
    }
}
