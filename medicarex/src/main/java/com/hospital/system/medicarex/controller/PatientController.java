package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.AppointmentDTO;
import com.hospital.system.medicarex.model.Patient;
import com.hospital.system.medicarex.model.User;
import com.hospital.system.medicarex.repository.DoctorRepository;
import com.hospital.system.medicarex.repository.PatientRepository;
import com.hospital.system.medicarex.repository.UserRepository;
import com.hospital.system.medicarex.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient")
@PreAuthorize("hasRole('PATIENT')")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Patient patient = getPatientFromPrincipal(userDetails);
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointmentService.getByPatient(patient));
        return "patient/dashboard";
    }

    @GetMapping("/book-appointment")
    public String bookAppointmentPage(Model model) {
        model.addAttribute("appointmentDto", new AppointmentDTO());
        model.addAttribute("doctors", doctorRepository.findAll());
        return "patient/book-appointment";
    }

    @PostMapping("/book-appointment")
    public String bookAppointment(@AuthenticationPrincipal UserDetails userDetails,
                                  @Valid @ModelAttribute("appointmentDto") AppointmentDTO dto,
                                  BindingResult result,
                                  RedirectAttributes redirectAttrs,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("doctors", doctorRepository.findAll());
            return "patient/book-appointment";
        }

        Patient patient = getPatientFromPrincipal(userDetails);
        appointmentService.bookAppointment(dto, patient);
        redirectAttrs.addFlashAttribute("successMsg", "Appointment booked successfully!");
        return "redirect:/patient/dashboard";
    }

    private Patient getPatientFromPrincipal(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow();
        return patientRepository.findByUser(user).orElseThrow();
    }
}
