package com.example.finalproject.dtos;

public class LoginResponse {

  private String username;
  private Integer greenBayDollars;

  public LoginResponse(String username, Integer greenBayDollars) {
    this.username = username;
    this.greenBayDollars = greenBayDollars;
  }

  public String getUsername() {
    return username;
  }

  public Integer getGreenBayDollars() {
    return greenBayDollars;
  }

}
