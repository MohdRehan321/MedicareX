package com.hospital.system.medicarex.repository;

import com.hospital.system.medicarex.enums.AppointmentStatus;
import com.hospital.system.medicarex.model.Appointment;
import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // For listing appointments of a patient
    List<Appointment> findByPatientId(Long patientId);

    // For safe ownership validation
    Optional<Appointment> findByIdAndPatientId(Long id, Long patientId);

    List<Appointment> findByDoctor(Doctor doctor); // ✅ used in getByDoctor

        List<Appointment> findByPatient(Patient patient); // ✅ used in getByPatient

        long countByStatus(AppointmentStatus status); // ✅ used in dashboard stats
}


