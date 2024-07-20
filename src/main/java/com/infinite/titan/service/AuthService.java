package com.infinite.titan.service;

import com.infinite.titan.reqres.LoginRequest;
import com.infinite.titan.reqres.LoginResponse;
import com.infinite.titan.entity.UserEntity;

public interface AuthService {
    UserEntity register(UserEntity userEntity);
    LoginResponse login(LoginRequest loginRequest);
}