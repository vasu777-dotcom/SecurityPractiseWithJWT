package com.example.SecurityPractiseWithJWT.service;

import com.example.SecurityPractiseWithJWT.entity.User;
import com.example.SecurityPractiseWithJWT.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User saved successfully";
    }

    public Set<String> getRoles(String username){
        return userRepository.findByUsername(username).getRoles();
    }

    public UserDetails loadUserByUsername(String username){
        User user = userRepository.getByUsername(username).orElseThrow();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
    }
}
