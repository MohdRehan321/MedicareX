package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.PatientDTO;
import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();

    PatientDTO getPatientById(Long id);

    PatientDTO addPatient(PatientDTO patientDTO);

    PatientDTO updatePatient(Long id, PatientDTO patientDTO);

    void deletePatient(Long id);
}

