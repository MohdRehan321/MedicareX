package com.hospital.system.medicarex.repository;

import com.hospital.system.medicarex.model.Patient;
import com.hospital.system.medicarex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
