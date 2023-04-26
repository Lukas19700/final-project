package com.example.finalproject.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private String username;
  private String password;
  private Integer greenBayDollars;
  @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
  private List<Item> item = new ArrayList<>();
  @OneToMany(mappedBy = "bidder", cascade = CascadeType.ALL)
  private List<Bid> bids = new ArrayList<>();

  public UserEntity(String username, String password, Integer greenBayDollars) {
    this.username = username;
    this.password = password;
    this.greenBayDollars = greenBayDollars;
  }

  public UserEntity() {

  }

  public Long getID() {
    return ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getGreenBayDollars() {
    return greenBayDollars;
  }

  public void setGreenBayDollars(Integer greenBayDollars) {
    this.greenBayDollars = greenBayDollars;
  }
}
