package com.hospital.system.medicarex.service;

import com.hospital.system.medicarex.dto.DoctorDTO;
import java.util.List;

public interface DoctorService {

    DoctorDTO createDoctor(Long userId, DoctorDTO dto);

    DoctorDTO getDoctor(Long id);

    List<DoctorDTO> getAllDoctors();

    DoctorDTO updateDoctor(Long id, DoctorDTO dto);

    void deleteDoctor(Long id);
}
