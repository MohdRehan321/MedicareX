package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.DepartmentDTO;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.DepartmentMapper;
import com.hospital.system.medicarex.model.Department;
import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.repository.DepartmentRepository;
import com.hospital.system.medicarex.repository.DoctorRepository;
import com.hospital.system.medicarex.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    // Constructor injection
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
    }

    // Create new department with optional head doctor
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department department = DepartmentMapper.toEntity(dto);

        if (dto.getHeadDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getHeadDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getHeadDoctorId()));
            department.setHeadDoctor(doctor);
        }
        return DepartmentMapper.toDTO(departmentRepository.save(department));
    }

    // Get all departments
    public
    List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDTO)
                .toList();
    }

    // Get department by ID
    public DepartmentDTO getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    // Update department name
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        dept.setName(dto.getName());
        departmentRepository.save(dept);
        return DepartmentMapper.toDTO(dept);
    }

    // Delete department
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    // Add doctor to department
    @Transactional
    public void addDoctorToDepartment(Long departmentId, Long doctorId) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        doctor.getDepartment().add(dept);
        doctorRepository.save(doctor);

        if (!doctor.getDepartment().contains(dept)) {
            doctor.getDepartment().add(dept);
        }
        dept.setHeadDoctor(doctor);
    }

    // Remove doctor from department
    public void removeDoctorFromDepartment(Long departmentId, Long doctorId) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
        dept.getDoctors().removeIf(doctor -> doctor.getId().equals(doctorId));
    }
}
