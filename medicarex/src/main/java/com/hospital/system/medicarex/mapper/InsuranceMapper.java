package com.hospital.system.medicarex.mapper;

import com.hospital.system.medicarex.dto.InsuranceDTO;
import com.hospital.system.medicarex.model.Insurance;

public class InsuranceMapper {
    public static Insurance toEntity(InsuranceDTO insuranceDTO) {
        if (insuranceDTO == null) return null;

        return Insurance.builder()
                .insuranceNo(insuranceDTO.getInsuranceNo())
                .provider(insuranceDTO.getProvider())
                .validUntil(insuranceDTO.getValidUntil())
                .build();
    }

    public static InsuranceDTO toDTO(Insurance insurance) {
        if (insurance == null) return null;

        return InsuranceDTO.builder()
                .id(insurance.getId())
                .insuranceNo(insurance.getInsuranceNo())
                .provider(insurance.getProvider())
                .validUntil(insurance.getValidUntil()).build();
    }
}

