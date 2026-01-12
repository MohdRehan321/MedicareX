package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.UserDTO;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.UserMapper;
import com.hospital.system.medicarex.model.User;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add new user
    @Override
    public UserDTO addUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(userRepository.save(user));
    }

    // Get user by ID
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return UserMapper.toDTO(user);
    }

    // Get all users
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    // Delete user by ID
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }
}

