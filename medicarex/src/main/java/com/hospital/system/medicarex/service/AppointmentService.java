package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.AppointmentDTO;
import java.util.List;


public interface AppointmentService  {

    List<AppointmentDTO> getAllAppointmentForPatient(Long patientId);

    AppointmentDTO getAppointmentById(Long patientId, Long appointmentId);

    AppointmentDTO addAppointment(Long patientId, Long doctorId, AppointmentDTO appointmentDTO );

    AppointmentDTO updateAppointment(Long patientId, Long appointmentId, AppointmentDTO appointmentDTO);

    void deleteAppointment(Long patientId, Long appointmentId);
}
