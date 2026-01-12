package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO dto);

    DepartmentDTO getDepartmentById(Long id);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updateDepartment(Long id, DepartmentDTO dto);

    void deleteDepartment(Long id);

    void addDoctorToDepartment(Long departmentId, Long doctorId);

    void removeDoctorFromDepartment(Long departmentId, Long doctorId);

}

