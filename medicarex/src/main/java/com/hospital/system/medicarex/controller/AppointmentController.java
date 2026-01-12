package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.AppointmentDTO;
import com.hospital.system.medicarex.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/{patientId}/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAll(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
                appointmentService.getAllAppointmentForPatient(patientId));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getById(
            @PathVariable Long patientId,
            @PathVariable Long appointmentId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentById(
                        patientId, appointmentId));
    }

    @PostMapping("/doctor/{doctorId}")
    public ResponseEntity<AppointmentDTO> create(
            @PathVariable Long patientId,
            @PathVariable Long doctorId,
            @Valid @RequestBody AppointmentDTO dto) {
        return new ResponseEntity<>(
                appointmentService.addAppointment(
                        patientId, doctorId, dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> update(
            @PathVariable Long patientId,
            @PathVariable Long appointmentId,
            @Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(
                appointmentService.updateAppointment(
                        patientId, appointmentId, dto));
    }

    @DeleteMapping("/{appointmentId}")
    public  ResponseEntity<Void> delete(
            @PathVariable Long patientId,
            @PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(
                patientId, appointmentId);
        return ResponseEntity.noContent().build();

    }

}

