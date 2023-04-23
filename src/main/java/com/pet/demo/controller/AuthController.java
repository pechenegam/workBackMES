package com.pet.demo.controller;

import com.pet.demo.dto.request.LoginRequest;
import com.pet.demo.dto.request.RegistrationRequest;
import com.pet.demo.dto.request.TokenRefreshRequest;
import com.pet.demo.dto.response.JwtResponse;
import com.pet.demo.dto.response.TokenRefreshResponse;
import com.pet.demo.security.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/sign-in")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return refreshTokenService.authenticateUser(loginRequest);
    }

    @PostMapping("/sign-up")
    public JwtResponse registrationUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return refreshTokenService.registrationUser(registrationRequest);
    }

    @PostMapping("/refresh")
    public TokenRefreshResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return refreshTokenService.refreshToken(request);
    }

}
