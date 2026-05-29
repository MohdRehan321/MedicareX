package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.UserDTO;
import com.hospital.system.medicarex.enums.Role;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.UserMapper;
import com.hospital.system.medicarex.model.User;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder; // ✅ correct import
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // ✅ Spring Security bean

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO addUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public void registerUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername()); // ✅ matches entity
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole()));

        userRepository.save(user);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email); // ✅ implement using repository
    }

}
