package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.DoctorDTO;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.DoctorMapper;
import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.model.User;
import com.hospital.system.medicarex.repository.DoctorRepository;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    // Constructor injection
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    // Create doctor linked to a user
    @Override
    public DoctorDTO createDoctor(Long userId, DoctorDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Doctor doctor = DoctorMapper.toEnity(dto, user);
        return DoctorMapper.toDTO(doctorRepository.save(doctor));
    }

    // Get doctor by ID
    public DoctorDTO getDoctor(Long id) {
        return doctorRepository.findById(id)
                .map(DoctorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
    }

    // Get all doctors
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toDTO)
                .toList();
    }

    // Delete doctor by ID
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        doctorRepository.delete(doctor);
    }

    // Update doctor details
    @Transactional
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());
        doctorRepository.save(doctor);
        return DoctorMapper.toDTO(doctor);
    }
}
