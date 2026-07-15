package com.inventory.platform.user.serviceimpl;

import com.inventory.platform.user.dto.LoginRequest;
import com.inventory.platform.user.dto.LoginResponse;
import com.inventory.platform.user.security.CustomUserDetailsService;
import com.inventory.platform.user.security.JwtService;
import com.inventory.platform.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationManager authenticationManager;



    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .message("Login Successful")
                .token(token)
                .build();
    }
}
