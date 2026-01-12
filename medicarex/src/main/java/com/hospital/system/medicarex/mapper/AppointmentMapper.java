package com.hospital.system.medicarex.mapper;

import com.hospital.system.medicarex.dto.AppointmentDTO;
import com.hospital.system.medicarex.model.Appointment;

public class AppointmentMapper {
    public static Appointment toEntity(AppointmentDTO appointmentDTO) {
        if (appointmentDTO == null) return null;

        return Appointment.builder()
                .appointmentDate(appointmentDTO.getAppointmentDate())
                .reason(appointmentDTO.getReason())
                .build();

    }

    public static AppointmentDTO toDTO(Appointment appointment) {
        if (appointment == null) return null;

        return AppointmentDTO.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .build();
    }
}
// Relationships are set in service layer



