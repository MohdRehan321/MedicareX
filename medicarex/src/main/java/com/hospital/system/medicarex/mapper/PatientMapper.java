package com.hospital.system.medicarex.mapper;

import com.hospital.system.medicarex.dto.PatientDTO;
import com.hospital.system.medicarex.model.Patient;

public class PatientMapper {
    // DTO → Entity (used during create/update)
    public static Patient toEntity(PatientDTO patientDTO) {
        if (patientDTO == null) return null;

        return Patient.builder()
                .name(patientDTO.getName())
                .birthDate(patientDTO.getBirthDate())
                .email(patientDTO.getEmail())
                .gender(patientDTO.getGender())
                .build(); // user set in service
    }

    // Entity → DTO (used for API responses)
    public static PatientDTO toDTO(Patient patient) {
        if (patient == null) return null;

        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .birthDate(patient.getBirthDate())
                .email(patient.getEmail())
                .gender(patient.getGender())
                .createdAt(patient.getCreatedAt())
                .insurance(
                        patient.getInsurance() != null
                                ? InsuranceMapper.toDTO(patient.getInsurance())
                                : null)
                .appointments(patient.getAppointments() != null
                        ? patient.getAppointments().stream()
                        .map(AppointmentMapper::toDTO)
                        .toList()
                        : null)
                .build();
    }


}

