package com.infinite.titan.controllers;

import com.infinite.titan.entity.UserEntity;
import com.infinite.titan.reqres.APIResponse;
import com.infinite.titan.reqres.LoginRequest;
import com.infinite.titan.reqres.LoginResponse;
import com.infinite.titan.security.JwtHelper;
import com.infinite.titan.service.AuthService;
import com.infinite.titan.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import static com.infinite.titan.constants.Endpoints.AUTHENTICATION_PATH;

@RestController
@RequestMapping(AUTHENTICATION_PATH)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            doAuthenticate(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

            // Generate JWT token
            String token = helper.generateToken(userDetails);

            // Create LoginResponse
            LoginResponse loginResponse = new LoginResponse(token, "Login successful", userMapper.toUser((UserEntity) userDetails));

            return ResponseEntity.ok(APIResponse.success(loginResponse, "Login successful"));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(APIResponse.error("Invalid credentials", null));
        }
    }

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserEntity>> register(@RequestBody UserEntity userEntity) {
        try {
            // Register new user
            UserEntity registeredUser = authService.register(userEntity);

            // Create success response
            return ResponseEntity.ok(APIResponse.success(registeredUser, "User registered successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(APIResponse.error("Registration failed", null));
        }
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse<Object>> handleBadCredentialsException(BadCredentialsException ex) {
        APIResponse<Object> response = APIResponse.error("Credentials Invalid!!", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}