package com.codewithdurgesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.repositories.RoleRepo;
import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.entities.Role;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		// Convert DTO to Entity
		User user = this.dtoToUser(userDto);

		// Encrypt password before saving
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));


		// Assign default role (Normal_user)

		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Id", AppConstants.NORMAL_USER));
		user.getRoles().add(role);

		// Save user
		User savedUser = this.userRepo.save(user);

		// Convert saved User entity back to DTO
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		// Update user fields
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());

		// Encrypt password if provided
		if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
			user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		} else {
			// If password is not provided, keep the existing one
			user.setPassword(user.getPassword());
		}

		user.setAbout(userDto.getAbout());

		// Save updated user
		User updatedUser = this.userRepo.save(user);

		// Convert updated User entity back to DTO
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		return users.stream()
				.map(this::userToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		// Remove associated roles
		user.getRoles().clear();

		// Save the user to update the relationship
		this.userRepo.save(user);

		// Now delete the user
		this.userRepo.delete(user);
	}


	private User dtoToUser(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);
	}

	private UserDto userToDto(User user) {
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		// Encode password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// Set roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Id", AppConstants.NORMAL_USER));
		user.getRoles().add(role);

		// Save new user
		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}
}
