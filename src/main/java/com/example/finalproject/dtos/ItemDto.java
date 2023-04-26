package com.example.finalproject.dtos;

import com.example.finalproject.models.Item;

public class ItemDto {

  private String name;
  private String description;
  private String photoUrl;
  private String sellerName;

  public ItemDto(Item item) {
    this.name = item.getName();
    this.description = item.getDescription();
    this.photoUrl = item.getPhotoUrl();
    this.sellerName = item.getSeller().getUsername();
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

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String buyerName) {
    this.sellerName = buyerName;
  }
}
