package com.example.job_portal_webApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomepage(){
        return "index";
    }

//    @GetMapping("/apply")
//    public String apply(){
//        return "apply";
//    }
}
