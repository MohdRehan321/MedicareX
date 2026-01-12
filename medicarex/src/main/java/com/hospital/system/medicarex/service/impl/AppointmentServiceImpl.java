package com.hospital.system.medicarex.service.impl;

import com.hospital.system.medicarex.dto.AppointmentDTO;
import com.hospital.system.medicarex.exceptions.ResourceNotFoundException;
import com.hospital.system.medicarex.mapper.AppointmentMapper;
import com.hospital.system.medicarex.model.Appointment;
import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.model.Patient;
import com.hospital.system.medicarex.repository.AppointmentRepository;
import com.hospital.system.medicarex.repository.DoctorRepository;
import com.hospital.system.medicarex.repository.PatientRepository;
import com.hospital.system.medicarex.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    // Constructor injection for repositories
    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // Get all appointments for a patient
    public List<AppointmentDTO> getAllAppointmentForPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(AppointmentMapper::toDTO)
                .toList();
    }

    // Get appointment by ID for a patient
    public AppointmentDTO getAppointmentById(Long patientId, Long appointmentId) {
        Appointment appointment = appointmentRepository
                .findByIdAndPatientId(appointmentId, patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment", "id", appointmentId));
        return AppointmentMapper.toDTO(appointment);
    }

    // Add new appointment for patient with doctor
    @Transactional
    public AppointmentDTO addAppointment(Long patientId, Long doctorId, AppointmentDTO dto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        Appointment appointment = AppointmentMapper.toEntity(dto);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return AppointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    // Delete appointment for patient
    public void deleteAppointment(Long patientId, Long appointmentId) {
        Appointment appointment = appointmentRepository
                .findByIdAndPatientId(appointmentId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));
        appointmentRepository.delete(appointment);
    }

    // Update appointment details
    public AppointmentDTO updateAppointment(Long patientId, Long appointmentId, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository
                .findByIdAndPatientId(appointmentId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());

        appointmentRepository.save(appointment);
        return AppointmentMapper.toDTO(appointment);
    }
}
