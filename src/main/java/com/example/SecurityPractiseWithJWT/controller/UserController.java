package com.example.SecurityPractiseWithJWT.controller;

import com.example.SecurityPractiseWithJWT.entity.User;
import com.example.SecurityPractiseWithJWT.entity.model.ResponseDto;
import com.example.SecurityPractiseWithJWT.entity.model.Token;
import com.example.SecurityPractiseWithJWT.entity.model.UserDetails;
import com.example.SecurityPractiseWithJWT.service.UserService;
import com.example.SecurityPractiseWithJWT.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil util;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<String>> registerUser(@Valid @RequestBody User user){
        String message = userService.registerUser(user);
        return ResponseEntity.ok(new ResponseDto<>("SUCCESS",message,null));
    }

    @GetMapping("/getRoles/{username}")
    public ResponseEntity<ResponseDto<Set<String>>> getRoles(@PathVariable String username){
        Set<String> roles = userService.getRoles(username);
        return ResponseEntity.ok(new ResponseDto<>("SUCCESS", roles, null));
    }


    @PostMapping("/token")
    public ResponseEntity<Token> generateToken(@RequestBody UserDetails userDetails){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
        org.springframework.security.core.userdetails.UserDetails iUserDetails = userDetailsService.loadUserByUsername(userDetails.getUsername());
        String token = util.generateToken(iUserDetails.getUsername());
        return ResponseEntity.ok(new Token(token));
    }
}
