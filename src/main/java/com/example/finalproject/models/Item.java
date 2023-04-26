package com.example.finalproject.models;

import com.example.finalproject.dtos.ItemCreation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private Boolean sold;
  private String name;
  private String description;
  private String photoUrl;
  private Integer startingPrice;
  private Integer purchasePrice;
  private String buyer;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity seller;
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  private List<Bid> bids = new ArrayList<>();

  public Item(String name, String description, String photoUrl, Integer startingPrice,
    Integer purchasePrice, UserEntity seller) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.seller = seller;
    this.sold = false;
  }

  public Item(ItemCreation itemCreation) {
    this.name = itemCreation.getName();
    this.description = itemCreation.getDescription();
    this.photoUrl = itemCreation.getPhotoUrl();
    this.startingPrice = itemCreation.getStartingPrice();
    this.purchasePrice = itemCreation.getPurchasePrice();
    this.sold = false;
  }

  public Item() {

  }

  public Integer getLastBidAmount() {
    if (this.getBids().isEmpty()) {
      return 0;
    } else {
      Bid lastBid = this.getBids().get(this.getBids().size() - 1);
      return lastBid != null ? lastBid.getAmount() : 0;
    }
  }

  public Long getID() {
    return ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
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

  public UserEntity getSeller() {
    return seller;
  }

  public void setSeller(UserEntity user) {
    this.seller = user;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }

  public Boolean getSold() {
    return sold;
  }

  public void setSold(Boolean sold) {
    this.sold = sold;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }
}
