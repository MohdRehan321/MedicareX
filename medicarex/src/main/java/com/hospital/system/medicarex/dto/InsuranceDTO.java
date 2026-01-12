package com.hospital.system.medicarex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class InsuranceDTO {
    private Long id;

    @NotBlank(message = "Insurance number is required")
    @Size(max = 50, message = "Insurance number must not exceed 50 characters")
    private String insuranceNo;

    @NotBlank(message = "Provider is required")
    @Size(max = 100, message = "Provider must not exceed 100 characters")
    private String provider;

    @NotNull(message = "Valid until date is required")
    private LocalDate validUntil;

    private LocalDateTime createdAt; // auto-generated, no validation
}
