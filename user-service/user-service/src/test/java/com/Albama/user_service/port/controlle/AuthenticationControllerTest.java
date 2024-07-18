package com.Albama.user_service.port.controlle;

import com.Albama.user_service.corn.model.AuthenticationRequest;
import com.Albama.user_service.corn.model.AuthenticationResponse;
import com.Albama.user_service.corn.service.imple.AuthenticationService;
import com.Albama.user_service.corn.service.imple.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @Autowired
    private MockMvc mockMvc;

    private String jwtToken;

    private static final String FIXED_JWT_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcyMTMwNTAwOCwiaWF0IjoxNzIxMzA1MDA4fQ.hj4TxErUeQRmxJYJO9yd0jYm2N4djtopzmWJWsI3yJM";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UserDetails userDetails = new User("admin@example.com", "admin123", Collections.emptyList());
        when(jwtService.generateToken(userDetails)).thenReturn("mocked-token");
        jwtToken = jwtService.generateToken(userDetails);
    }
/*
    @Test
    void testRegister() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("Admin");
        request.setLastName("User");
        request.setEmail("admin@example.com");
        request.setPassword("admin123");
        request.setAddress("123 Admin St");
        request.setCountry("Admin Country");
        request.setCity("Admin City");
        request.setZipCode("12345");

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("mocked-token");
        response.setAdmin(true);

        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(response);

        MvcResult result = mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtToken)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(new ObjectMapper().writeValueAsString(response), result.getResponse().getContentAsString());
    }

 */

    @Test
    void testAuthenticate() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("admin@example.com");
        request.setPassword("admin123");

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("mocked-token");
        response.setAdmin(true);

        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(response);

        MvcResult result = mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtToken)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(new ObjectMapper().writeValueAsString(response), result.getResponse().getContentAsString());
    }




    @Test
    void testRegister() throws Exception {
        String validJwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcyMTMwNTAwOCwiaWF0IjoxNzIxMzA1MDA4fQ.hj4TxErUeQRmxJYJO9yd0jYm2N4djtopzmWJWsI3yJM\n";

        mockMvc.perform(post("/register")
                        .header("Authorization", validJwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password\"}"))
                        .andExpect(status().isOk());
    }


}