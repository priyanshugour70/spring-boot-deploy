package com.infinite.titan.utils;

import com.infinite.titan.entity.UserEntity;
import com.infinite.titan.models.User;

public class UserMapper {

    // Convert UserEntity to User
    public static User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return User.builder()
                .id(userEntity.getId())
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .accountCreated(userEntity.getAccountCreated())
                .lastLogin(userEntity.getLastLogin())
                .profilePictureUrl(userEntity.getProfilePictureUrl())
                .dateOfBirth(userEntity.getDateOfBirth())
                .gender(userEntity.getGender())
                .build();
    }

    // Convert User to UserEntity
    public static UserEntity toUserEntity(User user) {
        if (user == null) {
            return null;
        }
        return UserEntity.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .accountCreated(user.getAccountCreated())
                .lastLogin(user.getLastLogin())
                .profilePictureUrl(user.getProfilePictureUrl())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .enabled(true) // Set the default value for enabled
                .build();
    }
}