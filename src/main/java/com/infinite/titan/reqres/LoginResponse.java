package com.infinite.titan.reqres;

import com.infinite.titan.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    // Token to be used for authentication
    private String token;

    // Message or status about the login attempt
    private String message;

    // User information (optional, depending on what you want to return)
    private User user;

}