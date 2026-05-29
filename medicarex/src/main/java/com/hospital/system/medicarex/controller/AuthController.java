package com.hospital.system.medicarex.controller;

import com.hospital.system.medicarex.dto.UserDTO;
import com.hospital.system.medicarex.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout,
                            Model model) {
        if (error != null) model.addAttribute("errorMsg", "Invalid email or password.");
        if (logout != null) model.addAttribute("logoutMsg", "You have been logged out.");
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDTO()); // ✅ use UserDTO
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDto") UserDTO dto,
                               BindingResult result,
                               RedirectAttributes redirectAttrs,
                               Model model) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) { // ✅ add confirmPassword in UserDTO
            result.rejectValue("confirmPassword", "error.userDto", "Passwords do not match.");
        }

        if (userService.emailExists(dto.getEmail())) { // ✅ add method in UserService
            result.rejectValue("email", "error.userDto", "Email is already registered.");
        }

        if (result.hasErrors()) {
            return "auth/register";
        }

        try {
            userService.registerUser(dto);
            redirectAttrs.addFlashAttribute("successMsg",
                    "Registration successful! Please login.");
            return "redirect:/auth/login?success";
        } catch (Exception e) {
            model.addAttribute("errorMsg", "Registration failed: " + e.getMessage());
            return "auth/register";
        }
    }
}
