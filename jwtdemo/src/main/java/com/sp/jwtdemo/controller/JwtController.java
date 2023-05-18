package com.sp.jwtdemo.controller;

import com.sp.jwtdemo.helper.JwtUtil;
import com.sp.jwtdemo.model.JwtRequest;
import com.sp.jwtdemo.model.JwtResponse;
import com.sp.jwtdemo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        String username = jwtRequest.getUsername();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}
