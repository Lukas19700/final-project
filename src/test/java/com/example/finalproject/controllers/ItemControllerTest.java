package com.example.finalproject.controllers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import com.example.finalproject.dtos.BidRequestDto;
import com.example.finalproject.dtos.ItemCreation;
import com.example.finalproject.dtos.SellableItemDto;
import com.example.finalproject.models.Item;
import com.example.finalproject.models.UserEntity;
import com.example.finalproject.services.ItemServiceImpl;
import com.example.finalproject.services.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

  @Mock
  private  ItemServiceImpl itemService;
  @InjectMocks
  private ItemController itemController;

  @Test
  public void testCreateItem() {
    ItemCreation itemCreation = new ItemCreation("name,", "des", "url", 10, 20);
    String token = "testToken";
    Item item = new Item("name,", "des", "url", 10, 20, new UserEntity());
    when(itemService.createSellableItem(itemCreation, token)).thenReturn(item);
    ResponseEntity responseEntity = itemController.createItem(itemCreation, token);
    assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void testGetSellableItem() {
    int page = 1;
    int pageSize = 20;
    List<SellableItemDto> sellableItems = new ArrayList<>();
    when(itemService.getSellableItems(page, pageSize)).thenReturn(sellableItems);
    ResponseEntity responseEntity = itemController.getSellableItem(page, pageSize);
    assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
  }
  @Test
  public void testGetSoldItem() {
    Long itemId = 1L;
    Item item = new Item();
    item.setSold(true);
    when(itemService.getItem(itemId)).thenReturn(item);
    ResponseEntity responseEntity = itemController.getItem(itemId);
    assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void testBidItem() {
    Long itemId = 1L;
    String token = "testToken";
    BidRequestDto bidRequestDto = new BidRequestDto(10);
    Item item = new Item("name,", "des", "url", 10, 20, new UserEntity());
    when(itemService.bidOnItem(itemId, token, bidRequestDto)).thenReturn("Sold");
    when(itemService.getItem(itemId)).thenReturn(item);
    ResponseEntity responseEntity = itemController.bidItem(itemId, bidRequestDto, token);
    assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void testBidItemJustBid() {
    Long itemId = 1L;
    String token = "testToken";
    BidRequestDto bidRequestDto = new BidRequestDto(10);
    Item item = new Item("name,", "des", "url", 10, 20, new UserEntity());
    when(itemService.bidOnItem(itemId, token, bidRequestDto)).thenReturn("JustBid");
    when(itemService.getItem(itemId)).thenReturn(item);
    ResponseEntity responseEntity = itemController.bidItem(itemId, bidRequestDto, token);
    assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
  }
}