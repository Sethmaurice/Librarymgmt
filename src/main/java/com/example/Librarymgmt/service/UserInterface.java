package com.example.Librarymgmt.service;


import com.example.Librarymgmt.beans.UserRegistrationBean;
import com.example.Librarymgmt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInterface extends UserDetailsService {
    public User saveAccount(UserRegistrationBean userRegistrationBean);
    public User findByEmail(String email);
}
