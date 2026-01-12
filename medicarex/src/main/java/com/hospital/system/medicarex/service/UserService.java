package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO addUser(UserDTO userDTO);

    void deleteUser(Long id);
}


