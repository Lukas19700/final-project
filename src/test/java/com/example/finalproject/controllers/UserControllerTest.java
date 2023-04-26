package com.example.finalproject.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.finalproject.dtos.AuthResponseDto;
import com.example.finalproject.dtos.LoginDto;
import com.example.finalproject.security.JwtTokenGenerator;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtTokenGenerator jwtTokenGenerator;

  @InjectMocks
  private UserController userController;

  @Test
  void testLogin() {
    LoginDto loginDto = new LoginDto("testuser", "testpassword");

    Authentication authentication = Mockito.mock(Authentication.class);
    Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);

    String token = "testtoken";
    Mockito.when(jwtTokenGenerator.generateToken(authentication)).thenReturn(token);

    ResponseEntity<AuthResponseDto> response = userController.login(loginDto);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(token, Objects.requireNonNull(response.getBody()).getAccessToken());
  }
}