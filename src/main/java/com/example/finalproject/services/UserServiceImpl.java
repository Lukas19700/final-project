package com.example.finalproject.services;

import com.example.finalproject.models.UserEntity;
import com.example.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity getUserByUsername(String username) {
    return userRepository.findUserByUsername(username)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }
}
