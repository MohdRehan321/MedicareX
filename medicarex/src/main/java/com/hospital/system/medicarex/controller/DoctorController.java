package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.enums.AppointmentStatus;
import com.hospital.system.medicarex.model.Doctor;
import com.hospital.system.medicarex.model.User;
import com.hospital.system.medicarex.repository.DoctorRepository;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasRole('DOCTOR')")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Doctor doctor = getDoctorFromPrincipal(userDetails);
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", appointmentService.getByDoctor(doctor));
        model.addAttribute("pendingCount",
                appointmentService.getByDoctor(doctor).stream()
                        .filter(a -> a.getStatus() == AppointmentStatus.PENDING));
        return "doctor/dashboard";
    }

    @GetMapping("/appointments")
    public String appointments(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Doctor doctor = getDoctorFromPrincipal(userDetails);
        model.addAttribute("appointments", appointmentService.getByDoctor(doctor));
        return "doctor/appointments";
    }

    @PostMapping("/appointments/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam AppointmentStatus status) {
        appointmentService.updateStatus(id, status);
        return "redirect:/doctor/appointments";
    }

    private Doctor getDoctorFromPrincipal(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow();
        return doctorRepository.findByUser(user).orElseThrow();
    }
}