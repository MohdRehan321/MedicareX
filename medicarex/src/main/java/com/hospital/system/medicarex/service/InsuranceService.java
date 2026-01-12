package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.InsuranceDTO;

public interface InsuranceService {

    InsuranceDTO getInsuranceForPatient(Long patientId);

    InsuranceDTO addInsuranceForPatient(Long patientId, InsuranceDTO insuranceDTO);

    InsuranceDTO updateInsuranceForPatient(Long patientId, Long insuranceId, InsuranceDTO insuranceDTO);

    void deleteInsuranceForPatient(Long patientId, Long insuranceId);

}


