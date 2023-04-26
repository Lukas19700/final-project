package com.example.finalproject.controllers;

import com.example.finalproject.dtos.BidRequestDto;
import com.example.finalproject.dtos.ItemCreation;
import com.example.finalproject.dtos.ItemDto;
import com.example.finalproject.dtos.ItemJustBidDto;
import com.example.finalproject.dtos.SoldItemDto;
import com.example.finalproject.models.Item;
import com.example.finalproject.services.ItemServiceImpl;
import com.example.finalproject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

  private final ItemServiceImpl itemService;
  private final UserServiceImpl userService;

  @Autowired
  public ItemController(ItemServiceImpl itemService, UserServiceImpl userService) {
    this.itemService = itemService;
    this.userService = userService;
  }

  @PostMapping("/create")
  public ResponseEntity createItem(@RequestBody ItemCreation itemCreation,
    @RequestHeader(name = "Authorization") String token) {
    Item item = itemService.createSellableItem(itemCreation, token);
    return ResponseEntity.ok(new ItemDto(item));
  }

  @GetMapping("/sellable-items")
  public ResponseEntity getSellableItem(@RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "20") int pageSize) {
    return ResponseEntity.ok(itemService.getSellableItems(page, pageSize));
  }

  @GetMapping("/item/{itemId}")
  public ResponseEntity getItem(@PathVariable Long itemId) {
    if (itemService.getItem(itemId).getSold()) {
      return ResponseEntity.ok(new SoldItemDto(itemService.getItem(itemId)));
    } else {
      return ResponseEntity.ok(new ItemDto(itemService.getItem(itemId)));
    }
  }

  @PostMapping("/bid/{itemId}")
  public ResponseEntity bidItem(@PathVariable Long itemId, @RequestBody BidRequestDto bidRequestDto,
    @RequestHeader(name = "Authorization") String token) {
    if (itemService.bidOnItem(itemId, token, bidRequestDto).equals("Sold")) {
      return ResponseEntity.ok(new SoldItemDto(itemService.getItem(itemId)));
    } else {
      return ResponseEntity.ok(new ItemJustBidDto(itemService.getItem(itemId)));
    }
  }
}
