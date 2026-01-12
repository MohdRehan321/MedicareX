package com.hospital.system.medicarex.repository;

import com.hospital.system.medicarex.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Used to enforce unique email constraint at service level
    boolean existsByEmail(String email);
}
