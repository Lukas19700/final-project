package com.example.finalproject.dtos;

import com.example.finalproject.models.Bid;
import com.example.finalproject.models.Item;

public class ItemJustBidDto {

  private String name;
  private String description;
  private String photoUrl;
  private Integer startingPrice;
  private Integer purchasePrice;
  private String buyer;
  private Integer lastBid;

  public ItemJustBidDto(Item item) {
    this.name = item.getName();
    this.description = item.getDescription();
    this.photoUrl = item.getPhotoUrl();
    this.startingPrice = item.getStartingPrice();
    this.purchasePrice = item.getPurchasePrice();
    this.buyer = item.getBuyer();
    if (item.getBids().isEmpty()) {
      this.lastBid = 0;
    } else {
      Bid bid = item.getBids().get(item.getBids().size() - 1);
      this.lastBid = bid.getAmount();
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Integer getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Integer startingPrice) {
    this.startingPrice = startingPrice;
  }

  public Integer getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(Integer purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }

  public Integer getLastBid() {
    return lastBid;
  }

  public void setLastBid(Integer lastBid) {
    this.lastBid = lastBid;
  }
}
