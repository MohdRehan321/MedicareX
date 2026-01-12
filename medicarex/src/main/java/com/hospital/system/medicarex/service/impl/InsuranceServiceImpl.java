package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.InsuranceDTO;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.InsuranceMapper;
import com.hospital.system.medicarex.model.Insurance;
import com.hospital.system.medicarex.model.Patient;
import com.hospital.system.medicarex.repository.InsuranceRepository;
import com.hospital.system.medicarex.repository.PatientRepository;
import com.hospital.system.medicarex.service.InsuranceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    // Constructor injection
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository,
                                PatientRepository patientRepository) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }

    // Get insurance details for a patient
    public InsuranceDTO getInsuranceForPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        if (patient.getInsurance() == null) {
            throw new ResourceNotFoundException("Insurance", "id", patientId);
        }
        return InsuranceMapper.toDTO(patient.getInsurance());
    }

    // Add insurance for a patient (only if none exists)
    @Transactional
    @Override
    public InsuranceDTO addInsuranceForPatient(Long patientId, InsuranceDTO dto ) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        if (patient.getInsurance() != null) {
            throw new IllegalStateException("Patient already has an insurance");
        }

        Insurance insurance = InsuranceMapper.toEntity(dto);
        insurance = insuranceRepository.save(insurance);

        patient.setInsurance(insurance);
        patientRepository.save(patient);
        return InsuranceMapper.toDTO(insurance);
    }

    // Delete insurance for a patient
    public void deleteInsuranceForPatient(Long patientId, Long insuranceId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        Insurance insurance = patient.getInsurance();
        if (insurance == null) {
            throw new ResourceNotFoundException("Insurance", "id", insuranceId);
        }

        patient.setInsurance(null);
        patientRepository.save(patient);
    }

    // Update insurance details for a patient
    public InsuranceDTO updateInsuranceForPatient(Long patientId, Long insuranceId, InsuranceDTO dto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        Insurance insurance = patient.getInsurance();
        if (insurance == null || !insurance.getId().equals(insuranceId)) {
            throw new ResourceNotFoundException("Insurance", "id", insuranceId);
        }

        insurance.setInsuranceNo(dto.getInsuranceNo());
        insurance.setProvider(dto.getProvider());
        insurance.setValidUntil(dto.getValidUntil());

        return InsuranceMapper.toDTO(insuranceRepository.save(insurance));
    }
}
