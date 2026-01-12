package com.hospital.system.medicarex.mapper;

import com.hospital.system.medicarex.dto.UserDTO;
import com.hospital.system.medicarex.model.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .username(dto.getUsername())
                .providerId(dto.getProviderId())
                .build();
    }

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .providerId(user.getProviderId())
                .build();
    }
}


