package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.DoctorDTO;
import com.hospital.system.medicarex.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<DoctorDTO> create(
            @PathVariable Long userId,
            @RequestBody DoctorDTO dto) {

        return new ResponseEntity<>(
                doctorService.createDoctor(userId, dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAll(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorDTO dto){
        return ResponseEntity.ok(
                doctorService.updateDoctor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

}
