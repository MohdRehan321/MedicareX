package com.hospital.system.medicarex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

    @GetMapping({"/", "/index"})
    public String landing() {
        return "index";
    }

    @GetMapping("/error/403")
    public String accessDenied() {
        return "error/403";
    }
}