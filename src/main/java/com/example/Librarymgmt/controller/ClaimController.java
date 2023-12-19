package com.example.Librarymgmt.controller;


import com.example.Librarymgmt.model.Claim;
import com.example.Librarymgmt.service.ClaimInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClaimController {
private final ClaimInterface claimInterface;
private ClaimController(ClaimInterface claimInterface) {
        this.claimInterface = claimInterface;
    }

    @GetMapping("/claim")
    public String viewClaim(Model model){
        model.addAttribute("claim", new Claim());
        return "claimus";
    }

    @GetMapping("/display")
    public String displayClaim(Model model){
    model.addAttribute("display",claimInterface.GetAllClaim());
    return "displayClaim";
    }

    @PostMapping("/regClaim")
    public String saveClaimm(@ModelAttribute("claim") Claim claim){
        Claim claim1 = claimInterface.saveClaim(claim);
        if(claim1 != null) {
            return "redirect:/claim?success";
        }else {
            return "redirect:/claim?error";
        }
    }
    @GetMapping("/display/{emaill}")
    public String deleteClaim(@PathVariable String emaill){
    claimInterface.deleteClaim(emaill);
    return "redirect:/display";
    }
}
