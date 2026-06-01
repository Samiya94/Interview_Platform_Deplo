package com.interviewPlatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import com.interviewPlatform.repositories.InstituteRepository;
import com.interviewPlatform.repositories.InterviewerRepository;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final InstituteRepository instituteRepository;
    private final InterviewerRepository interviewerRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("institutes", instituteRepository.findAllWithDepartments());
        model.addAttribute("interviewers", interviewerRepository.findAllWithSkills());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/register/mentor")
    public String mentorRegistrationPage(@RequestParam String token, @RequestParam Long inst, Model model) {
        model.addAttribute("token", token);
        model.addAttribute("instituteId", inst);
        return "mentor-registration";
    }

    @GetMapping("/register/student")
    public String studentRegistrationPage() {
        return "student-registration";
    }

    @GetMapping("/register/interviewer")
    public String interviewerRegistrationPage() {
        return "register";   // reuses your existing register.html which has the interviewer tab
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @GetMapping("/institute-dashboard")
    public String instituteDashboard() {
        return "institute-dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/student-dashboard")
    public String studentDashboard() {
        return "student-dashboard";
    }

    @GetMapping("/mentor-dashboard")
    public String mentorDashboard() {
        return "mentor-dashboard";
    }

    @GetMapping("/interviewer-dashboard")
    public String interviewerDashboard() {
        return "interviewer-dashboard";
    }

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login";
    }
}