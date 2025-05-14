package com.example.job_portal_webApp.controller;

import com.example.job_portal_webApp.model.JobApplication;
import com.example.job_portal_webApp.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;


    //    show the application form
    @GetMapping("/apply")
    public String showform(Model model){
        model.addAttribute("jobApplication", new JobApplication());
        return "application-form";
    }

//    handle form submission
    @PostMapping("/apply")
    public String submitForm(@ModelAttribute JobApplication jobApplication, Model model){
        try {
            jobApplicationService.saveApplication(jobApplication);
            model.addAttribute("message", "Application submitted successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to submit application: " + e.getMessage());
        }
        return "application-form";
    }
}
