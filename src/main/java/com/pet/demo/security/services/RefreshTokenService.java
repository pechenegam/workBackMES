package com.pet.demo.security.services;

import com.pet.demo.dto.request.LoginRequest;
import com.pet.demo.dto.request.RegistrationRequest;
import com.pet.demo.dto.request.TokenRefreshRequest;
import com.pet.demo.dto.response.JwtResponse;
import com.pet.demo.dto.response.TokenRefreshResponse;
import com.pet.demo.entity.RefreshToken;
import com.pet.demo.entity.Role;
import com.pet.demo.entity.User;
import com.pet.demo.exception.EntityNotFoundException;
import com.pet.demo.exception.TokenRefreshException;
import com.pet.demo.repository.RefreshTokenRepository;
import com.pet.demo.repository.RoleRepository;
import com.pet.demo.repository.UserRepository;
import com.pet.demo.security.jwt.JwtUtils;
import com.pet.demo.utils.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    public static final String REFRESH_TOKEN_EXPIRED_MESSAGE = "Refresh token was expired. Please make a new sign-in request";
    private static final String EXCEPTION_SERVICE = "RefreshToken";
    private static final String TOKEN_TYPE = "Bearer";
    public static final String REFRESH_TOKEN_NOT_FOUND_EXCEPTION = "Refresh token is not in database!";
    @Value("${test.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return findByToken(requestRefreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername((user).getUsername());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, REFRESH_TOKEN_NOT_FOUND_EXCEPTION));
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, userId)));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        RefreshToken refreshToken = createRefreshToken(userDetails.getId());
        return JwtResponse.builder()
                .token(jwt)
                .type(TOKEN_TYPE)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .roles(roles)
                .refreshToken(refreshToken.getToken())
                .build();

    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), REFRESH_TOKEN_EXPIRED_MESSAGE);
        }
        return token;
    }

    @Transactional
    public JwtResponse registrationUser(RegistrationRequest signupRequest) {
        if (isUserNameAlreadyExists(signupRequest) || isEmailAlreadyExists(signupRequest)){
            throw new RuntimeException("username or email already in use");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER));
        user.setRoles(roles);
        userRepository.save(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(signupRequest.getPassword());
        loginRequest.setUsername(signupRequest.getUsername());
        return authenticateUser(loginRequest);
    }


    private Boolean isEmailAlreadyExists(RegistrationRequest registrationRequest) {
        return userRepository.existsByEmail(registrationRequest.getEmail());
    }

    private Boolean isUserNameAlreadyExists(RegistrationRequest registrationRequest) {
        return userRepository.existsByUsername(registrationRequest.getUsername());
    }
}
