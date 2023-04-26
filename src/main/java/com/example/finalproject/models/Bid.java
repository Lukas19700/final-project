package com.example.finalproject.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bids")
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bidder_id")
  private UserEntity bidder;
  private Integer amount;
  private LocalDateTime timestamp;

  public Bid(Integer amount, LocalDateTime timestamp) {
    this.amount = amount;
    this.timestamp = timestamp;
  }

  public Bid() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public UserEntity getBidder() {
    return bidder;
  }

  public void setBidder(UserEntity bidder) {
    this.bidder = bidder;
  }
}
