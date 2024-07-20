package com.infinite.titan.repository;

import com.infinite.titan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Find a user by username
    Optional<UserEntity> findByUsername(String username);

    // Find a user by email
    Optional<UserEntity> findByEmail(String email);

    // Find a user by userId
    Optional<UserEntity> findByUserId(String userId);
}