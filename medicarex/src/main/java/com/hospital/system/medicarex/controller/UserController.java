package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.DashboardStatsDTO;
import com.hospital.system.medicarex.enums.AppointmentStatus;
import com.hospital.system.medicarex.enums.Role;
import com.hospital.system.medicarex.repository.AppointmentRepository;
import com.hospital.system.medicarex.repository.DoctorRepository;
import com.hospital.system.medicarex.repository.PatientRepository;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardStatsDTO stats = new DashboardStatsDTO(
                userRepository.countByRole(Role.ROLE_DOCTOR),
                userRepository.countByRole(Role.ROLE_PATIENT),
                appointmentRepository.count(),
                appointmentRepository.countByStatus(AppointmentStatus.PENDING),
                appointmentRepository.countByStatus(AppointmentStatus.COMPLETED)
        );
        model.addAttribute("stats", stats);
        model.addAttribute("recentAppointments",
                appointmentService.getAll().stream().limit(10).toList());
        return "admin/dashboard";
    }

    @GetMapping("/doctors")
    public String manageDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "admin/doctors";
    }

    @GetMapping("/patients")
    public String managePatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "admin/patients";
    }

    @PostMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/admin/doctors";
    }
}