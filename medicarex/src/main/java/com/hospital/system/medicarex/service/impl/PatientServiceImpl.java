package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.PatientDTO;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.PatientMapper;
import com.hospital.system.medicarex.model.Patient;
import com.hospital.system.medicarex.model.User;
import com.hospital.system.medicarex.repository.PatientRepository;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    // Constructor injection
    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    // Get all patients
    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    // Get patient by ID
    public PatientDTO getPatientById(Long patientId ) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
        return PatientMapper.toDTO(patient);
    }

    // Add new patient
    public PatientDTO addPatient(PatientDTO patientDTO) {
        User user = userRepository.findById(patientDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User","id",patientDTO.getUserId()));

        Patient patient = PatientMapper.toEntity(patientDTO);
        patient.setUser(user);

        return PatientMapper.toDTO(patientRepository.save(patient));

    }

    // Delete patient by ID
    @Override
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
        patientRepository.delete(patient);
    }

    // Update patient details
    public PatientDTO updatePatient(Long patientId, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        patient.setName(patientDTO.getName());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setEmail(patientDTO.getEmail());
        patient.setGender(patientDTO.getGender());
        patient.setCreatedAt(patientDTO.getCreatedAt());

        return PatientMapper.toDTO(patientRepository.save(patient));
    }
}
