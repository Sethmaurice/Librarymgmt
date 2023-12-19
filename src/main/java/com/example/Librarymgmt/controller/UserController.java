package com.example.Librarymgmt.controller;


import com.example.Librarymgmt.beans.UserRegistrationBean;
import com.example.Librarymgmt.model.User;
import com.example.Librarymgmt.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Lazy
@Controller
@RequestMapping("/registration")
public class UserController {
    private final UserInterface userInterface;
    @Autowired
    public UserController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
    @GetMapping
    public String showRegistrationForm(){
        return "signup";
    }

    @ModelAttribute("user")
    public UserRegistrationBean userRegistrationBean(){
        return new UserRegistrationBean();
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationBean userRegistrationBeans){
        User userfound = userInterface.findByEmail(userRegistrationBeans.getEmail());
        if (userfound != null && (userfound.getEmail().equalsIgnoreCase((userRegistrationBeans.getEmail())))){
            return "redirect:/registration?error";
        }
        userInterface.saveAccount(userRegistrationBeans);
        return "redirect:/registration?success";
    }

}
