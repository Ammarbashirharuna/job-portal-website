package com.example.job_portal_webApp.controller;

import com.example.job_portal_webApp.model.Admin;
import com.example.job_portal_webApp.model.JobApplication;
import com.example.job_portal_webApp.repository.AdminRepository;
import com.example.job_portal_webApp.service.JobApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JobApplicationService jobApplicationService;

    // Show admin login form
    @GetMapping("/admin/login")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-login";
    }

    // Process admin login
    @PostMapping("/admin/login")
    public String processLogin(@ModelAttribute Admin admin, HttpSession session, Model model) {
        Admin found = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (found != null) {
            session.setAttribute("loggedInAdmin", found);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Invalid credentials");
        return "admin-login";
    }

    // Admin dashboard
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        List<JobApplication> applications = jobApplicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "admin-dashboard";
    }


    // Logout admin
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
