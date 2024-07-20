package com.infinite.titan.service.impl;

import com.infinite.titan.entity.UserEntity;
import com.infinite.titan.reqres.LoginRequest;
import com.infinite.titan.reqres.LoginResponse;
import com.infinite.titan.repository.UserRepository;
import com.infinite.titan.security.CustomUserDetails;
import com.infinite.titan.security.JwtHelper;
import com.infinite.titan.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public UserEntity register(UserEntity userEntity) {
        userEntity.setUserId(userEntity.getUsername() + "_" + UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

            // Generate JWT token
            String token = jwtHelper.generateToken(userDetails);

            // Prepare login response
            return new LoginResponse(token, "Login successful", userDetails);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!!");
        }
    }
}