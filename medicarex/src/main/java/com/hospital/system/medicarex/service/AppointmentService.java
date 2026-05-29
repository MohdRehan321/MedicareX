package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.AppointmentDTO;
import com.hospital.system.medicarex.enums.AppointmentStatus;
import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.model.Patient;

import java.util.List;


public interface AppointmentService  {

    List<AppointmentDTO> getAllAppointmentForPatient(Long patientId);

    AppointmentDTO getAppointmentById(Long patientId, Long appointmentId);

    AppointmentDTO addAppointment(Long patientId, Long doctorId, AppointmentDTO appointmentDTO );

    AppointmentDTO updateAppointment(Long patientId, Long appointmentId, AppointmentDTO appointmentDTO);

    void deleteAppointment(Long patientId, Long appointmentId);

    List<AppointmentDTO> getByDoctor(Doctor doctor);   // ✅ fetch appointments for doctor

    void updateStatus(Long appointmentId, AppointmentStatus status); // ✅ update appointment status

    List<AppointmentDTO> getByPatient(Patient patient);   // ✅ fetch appointments for patient

    void bookAppointment(AppointmentDTO dto, Patient patient); // ✅ book appointment

    List<AppointmentDTO> getAll(); // ✅ used in dashboard


}




