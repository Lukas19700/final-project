package com.example.finalproject.services;

import com.example.finalproject.dtos.BidRequestDto;
import com.example.finalproject.dtos.ItemCreation;
import com.example.finalproject.dtos.SellableItemDto;
import com.example.finalproject.models.Bid;
import com.example.finalproject.models.Item;
import com.example.finalproject.models.UserEntity;
import com.example.finalproject.repositories.BidRepository;
import com.example.finalproject.repositories.ItemRepository;
import com.example.finalproject.repositories.UserRepository;
import com.example.finalproject.security.JwtTokenGenerator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl {

  private final ItemRepository itemRepository;
  private final BidRepository bidRepository;
  private final JwtTokenGenerator jwtTokenGenerator;
  private final UserRepository userRepository;

  @Autowired
  public ItemServiceImpl(ItemRepository itemRepository, BidRepository bidRepository,
    JwtTokenGenerator jwtTokenGenerator, UserRepository userRepository) {
    this.itemRepository = itemRepository;
    this.bidRepository = bidRepository;
    this.jwtTokenGenerator = jwtTokenGenerator;
    this.userRepository = userRepository;
  }

  public Item createSellableItem(ItemCreation itemCreation, String token) {
    if (itemCreation.getName() == null || itemCreation.getDescription() == null
      || itemCreation.getPhotoUrl() == null
      || itemCreation.getStartingPrice() == null || itemCreation.getPurchasePrice() == null) {
      throw new IllegalArgumentException("Properties cannot be null or empty");
    }
    Item item = new Item(itemCreation);
    String username = jwtTokenGenerator.getUsernameFromJWT(token.substring(7));
    if (userRepository.findUserByUsername(username).isPresent()) {
      item.setSeller(userRepository.findUserByUsername(username).get());
    }
    itemRepository.save(item);
    return item;
  }

  public List<SellableItemDto> getSellableItems(int page, int pageSize) {
    if (page < 1) {
      throw new IllegalArgumentException("The page is incorrect");
    }
    int startIndex = (page - 1) * pageSize;
    int endIndex = startIndex + pageSize;
    List<SellableItemDto> items = itemRepository.findBySoldFalse()
      .stream()
      .skip(startIndex)
      .limit(pageSize)
      .map(SellableItemDto::new)
      .collect(Collectors.toList());
    return items;
  }

  public Item getItem(Long itemId) {
    if (!itemRepository.findById(itemId).isPresent()) {
      throw new IllegalArgumentException("Item is not found");
    }
    Item item = new Item();
    if (itemRepository.findById(itemId).isPresent()) {
      item = itemRepository.findById(itemId).get();
    }
    return item;
  }

  public String bidOnItem(Long itemId, String token, BidRequestDto bidRequestDto) {
    Item item = new Item();
    UserEntity user = new UserEntity();
    if (userRepository.findUserByUsername(jwtTokenGenerator.getUsernameFromJWT(token.substring(7)))
      .isPresent()) {
      user = userRepository.findUserByUsername(
        jwtTokenGenerator.getUsernameFromJWT(token.substring(7))).get();
    }
    if (itemRepository.findById(itemId).isPresent()) {
      item = itemRepository.findById(itemId).get();
    }
    if (!itemRepository.findById(itemId).isPresent()) {
      throw new IllegalArgumentException("Item is not found");
    } else if (itemRepository.findById(itemId).isPresent() && item.getSold()) {
      throw new IllegalArgumentException("Item is sold");
    } else if (itemRepository.findById(itemId).isPresent() && !item.getSold() && (
      user.getGreenBayDollars() < bidRequestDto.getBidAmount())) {
      throw new IllegalArgumentException("There is not enough greenbay dollars on account");
    } else if (itemRepository.findById(itemId).isPresent() && !item.getSold() && (
      item.getLastBidAmount() >= bidRequestDto.getBidAmount())) {
      throw new IllegalArgumentException("Bid is too low");
    } else if (itemRepository.findById(itemId).isPresent() && !item.getSold() && (
      item.getLastBidAmount() < bidRequestDto.getBidAmount()
        && bidRequestDto.getBidAmount() < item.getPurchasePrice()
        && user.getGreenBayDollars() > bidRequestDto.getBidAmount())) {
      Bid bid = new Bid(bidRequestDto.getBidAmount(), LocalDateTime.now());
      bid.setItem(item);
      bid.setBidder(user);
      item.getBids().add(bid);
      bidRepository.save(bid);
      itemRepository.save(item);
      return "Bid";
    } else if (itemRepository.findById(itemId).isPresent() && !item.getSold()
      && bidRequestDto.getBidAmount() >= item.getPurchasePrice()
      && user.getGreenBayDollars() > bidRequestDto.getBidAmount()) {
      item.setSold(true);
      Bid bid = new Bid(bidRequestDto.getBidAmount(), LocalDateTime.now());
      item.getBids().add(bid);
      bidRepository.save(bid);
      item.setBuyer(user.getUsername());
      itemRepository.save(item);
    }
    return "Sold";
  }
}
