package com.example.finalproject.controllers;

import com.example.finalproject.dtos.AuthResponseDto;
import com.example.finalproject.dtos.LoginDto;
import com.example.finalproject.security.JwtTokenGenerator;
import com.example.finalproject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserServiceImpl userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenGenerator jwtTokenGenerator;

  @Autowired
  public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder,
    AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtTokenGenerator = jwtTokenGenerator;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenGenerator.generateToken(authentication);
    return new ResponseEntity(new AuthResponseDto(token), HttpStatus.OK);
  }
}
