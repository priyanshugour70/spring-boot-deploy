package com.infinite.titan.reqres;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    // Username or email used for login
    @NotBlank(message = "Username or email is required")
    private String usernameOrEmail;

    // Password for login
    @NotBlank(message = "Password is required")
    private String password;
}