package com.hospital.system.medicarex.mapper;

import com.hospital.system.medicarex.dto.DoctorDTO;
import com.hospital.system.medicarex. model.Doctor;
import com.hospital.system.medicarex.model.User;

public class DoctorMapper {

    public static Doctor toEnity(DoctorDTO doctorDTO, User user) {
        if (doctorDTO == null) return null;

        return Doctor.builder()
                .user(user)
                .name(doctorDTO.getName())
                .specialization(doctorDTO.getSpecialization())
                .email(doctorDTO.getEmail())
                .build();
    }

    public static DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) return null;

        return DoctorDTO.builder()
                .id(doctor.getId())
                .userId(doctor.getUser().getId())
                .name(doctor.getName())
                .specialization(doctor.getSpecialization())
                .email(doctor.getEmail())
                .build();
    }


}

