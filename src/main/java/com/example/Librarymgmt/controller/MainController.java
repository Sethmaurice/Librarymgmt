package com.example.Librarymgmt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/homeDashboard")
    public String homepage(){
        return "templa";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/homee")
    public String welcomePage(){
        return "hom";
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("isAuthenticated()")
    public String dashboard() {
        // Controller logic
        return "redirect:/homeDashboard";
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public String admin() {
        // Controller logic
        return "homes";
    }
}
