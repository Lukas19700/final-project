package com.example.finalproject.dtos;

import com.example.finalproject.models.Item;

public class ItemResponseDto {

  private String name;

  private String description;

  private String photoUrl;

  private int startingPrice;

  private int purchasePrice;

  public ItemResponseDto(Item item) {
    this.name = item.getName();
    this.description = item.getDescription();
    this.photoUrl = item.getPhotoUrl();
    this.startingPrice = item.getStartingPrice();
    this.purchasePrice = item.getPurchasePrice();
  }

  public ItemResponseDto() {
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

  public int getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(int startingPrice) {
    this.startingPrice = startingPrice;
  }

  public int getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(int purchasePrice) {
    this.purchasePrice = purchasePrice;
  }
}
