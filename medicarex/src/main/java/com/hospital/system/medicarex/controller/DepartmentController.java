package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.DepartmentDTO;
import com.hospital.system.medicarex.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> add(@Valid @RequestBody DepartmentDTO dto) {
        return new ResponseEntity<>(departmentService.createDepartment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @Valid @RequestBody DepartmentDTO dto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{deptId}/doctors/{doctorId}")
    public ResponseEntity<Void> addDoctor(@PathVariable Long deptId, @PathVariable Long doctorId) {
        departmentService.addDoctorToDepartment(deptId, doctorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{deptId}/doctors/{doctorsId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long deptId, @PathVariable Long doctorsId) {
        departmentService.removeDoctorFromDepartment(deptId, doctorsId);
        return ResponseEntity.noContent().build();
    }
}
