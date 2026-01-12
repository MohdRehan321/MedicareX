package com.hospital.system.medicarex.mapper;

import com.hospital.system.medicarex.dto.DepartmentDTO;
import com.hospital.system.medicarex.model.Department;


public class DepartmentMapper {

    public static Department toEntity(DepartmentDTO deptDTO) {
        if (deptDTO == null) return null;

        return Department.builder()
                .id(deptDTO.getId())
                .name(deptDTO.getName())
                // headDoctor is set in SERVICE, not here
                .build();
    }


    public static DepartmentDTO toDTO(Department dept) {
        if (dept == null) return null;

        return DepartmentDTO.builder()
                .id(dept.getId())
                .name(dept.getName())
                .headDoctorId(
                        dept.getHeadDoctor() != null
                                ? dept.getHeadDoctor().getId()
                                : null)
                .build();
    }
}





