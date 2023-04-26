package com.example.finalproject.dtos;


import com.example.finalproject.models.Item;

public class SellableItemDto {

  private String name;

  private String photoUrl;

  private Integer lastBid;

  public SellableItemDto(Item item) {
    this.name = item.getName();
    this.photoUrl = item.getPhotoUrl();
    this.lastBid = item.getLastBidAmount();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Integer getLastBid() {
    return lastBid;
  }

  public void setLastBid(Integer lastBid) {
    this.lastBid = lastBid;
  }
}
