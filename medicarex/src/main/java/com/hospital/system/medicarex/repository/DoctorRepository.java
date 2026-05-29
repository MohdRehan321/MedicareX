package com.hospital.system.medicarex.repository;

import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
        Optional<Doctor> findByUser(User user); // ✅ used in getDoctorFromPrincipal
}



