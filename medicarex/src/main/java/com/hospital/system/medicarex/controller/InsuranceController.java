package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.InsuranceDTO;
import com.hospital.system.medicarex.service.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients/{patientId}/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public ResponseEntity<InsuranceDTO> getById(@PathVariable Long patientId) {
        return ResponseEntity.ok(insuranceService.getInsuranceForPatient(patientId));
    }

    @PostMapping()
    public ResponseEntity<InsuranceDTO> add(@PathVariable Long patientId, @Valid @RequestBody InsuranceDTO insuranceDTO) {
        return new ResponseEntity<>(insuranceService.addInsuranceForPatient(patientId, insuranceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{insuranceId}")
    public ResponseEntity<InsuranceDTO> update(@PathVariable Long patientId, @PathVariable Long insuranceId, @Valid @RequestBody InsuranceDTO insuranceDTO) {
        return ResponseEntity.ok(insuranceService.updateInsuranceForPatient(patientId, insuranceId, insuranceDTO));
    }

    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<Void> delete(@PathVariable Long patientId, @PathVariable Long insuranceId) {
        insuranceService.deleteInsuranceForPatient(patientId, insuranceId);
        return ResponseEntity.noContent().build();
    }
}
