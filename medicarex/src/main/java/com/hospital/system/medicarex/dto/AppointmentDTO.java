package com.hospital.system.medicarex.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AppointmentDTO {
    @NotNull(message = "Doctor is required")
    private Long id;


    @NotNull(message = "Appointment date is required")
    @Future(message = "Appointment date must be in the future")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate appointmentDate;

    @NotBlank(message = "Reason is required")
    @Size(max = 255, message = "Reason must not exceed 255 characters")
    private String reason;
}
