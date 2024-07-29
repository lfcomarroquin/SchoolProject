package com.api.schoolproject.service;

import com.api.schoolproject.config.JwtService;
import com.api.schoolproject.dto.auth.AuthDto;
import com.api.schoolproject.dto.auth.LoginDto;
import com.api.schoolproject.dto.auth.RegisterDto;
import com.api.schoolproject.entity.SchoolEntity;
import com.api.schoolproject.repository.SchoolRepository;
import com.api.schoolproject.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthDto login (final LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = schoolRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthDto(token);
    }

    public AuthDto register (final RegisterDto request) {
        SchoolEntity user = new SchoolEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        schoolRepository.save(user);
        return new AuthDto(this.jwtService.getToken(user));
    }

}