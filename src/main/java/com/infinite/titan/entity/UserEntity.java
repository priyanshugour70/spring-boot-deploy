package com.infinite.titan.entity;

import com.infinite.titan.models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    // ID generated by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // UserEntity ID created by controller
    @NotBlank(message = "UserEntity Id is required")
    @Size(min = 4, max = 30, message = "UserEntity Id must be between 4 and 30 characters")
    @Column(unique = true, nullable = false)
    private String userId;

    // Username
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @Column(unique = true, nullable = false)
    private String username;

    // Password
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    // First name
    @NotBlank(message = "First name is required")
    private String firstName;

    // Last name
    @NotBlank(message = "Last name is required")
    private String lastName;

    // Email
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    // Role
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;

    // Account created date
    @NotNull(message = "Account creation date is required")
    private LocalDateTime accountCreated;

    // Last login date
    private LocalDateTime lastLogin;

    // Profile picture URL
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    // Date of birth
    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    // Gender
    @Column(name = "gender")
    private String gender;

    // Enabled
    @Column(name = "enabled")
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }
}