package com.example.finalproject.dtos;

import java.math.BigDecimal;

public class BidDto {

  private String bidderName;

  private BigDecimal amount;

  public BidDto(String bidderName, BigDecimal amount) {
    this.bidderName = bidderName;
    this.amount = amount;
  }

  public String getBidderName() {
    return bidderName;
  }

  public void setBidderName(String bidderName) {
    this.bidderName = bidderName;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
