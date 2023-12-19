package com.example.Librarymgmt.service.implementation;


import com.example.Librarymgmt.beans.UserRegistrationBean;
import com.example.Librarymgmt.model.Role;
import com.example.Librarymgmt.model.User;
import com.example.Librarymgmt.repo.UserRepo;
import com.example.Librarymgmt.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserInterface {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    @Autowired
    public void setDependencies(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User saveAccount(UserRegistrationBean userRegistrationBean) {
        User user = new User(userRegistrationBean.getfName(), userRegistrationBean.getlName(),
                userRegistrationBean.getEmail(),
                passwordEncoder.encode(userRegistrationBean.getPassword()), Arrays.asList(new Role("ROLE_ADMIN")));
        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("invalid username or password");
        } else
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    mapRoleToGrantedAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToGrantedAuthority(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
