package com.example.finalproject.dtos;

import com.example.finalproject.models.UserEntity;

public class UserDto {

  private String username;

  public UserDto(UserEntity username) {
    this.username = String.valueOf(username);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
