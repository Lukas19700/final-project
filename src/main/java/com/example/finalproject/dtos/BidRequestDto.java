package com.example.finalproject.dtos;

public class BidRequestDto {

  private Integer bidAmount;

  public BidRequestDto(Integer bidAmount) {
    this.bidAmount = bidAmount;
  }

  public Integer getBidAmount() {
    return bidAmount;
  }

  public BidRequestDto() {
  }

  public void setBidAmount(Integer bidAmount) {
    this.bidAmount = bidAmount;
  }
}
