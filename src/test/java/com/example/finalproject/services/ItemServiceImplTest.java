package com.example.finalproject.services;

import static org.mockito.Mockito.when;

import com.example.finalproject.dtos.ItemCreation;
import com.example.finalproject.models.Item;
import com.example.finalproject.models.UserEntity;
import com.example.finalproject.repositories.ItemRepository;
import com.example.finalproject.repositories.UserRepository;
import com.example.finalproject.security.JwtTokenGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

  @Mock
  private ItemRepository itemRepository;
  @Mock
  private UserRepository userRepository;
  @Mock
  private JwtTokenGenerator jwtTokenGenerator;
  @InjectMocks
  private ItemServiceImpl itemService;

  @Test
  public void test_get_sellable_items() {
    int page = 1;
    int pageSize = 20;
    List<Item> items = new ArrayList<>();
    items.add(new Item("name", "description", "url", 10, 5, new UserEntity()));
    items.add(new Item("name1", "description1", "url1", 20, 10, new UserEntity()));
    items.add(new Item("name2", "description2", "url2", 30, 15, new UserEntity()));

    when(itemRepository.findBySoldFalse()).thenReturn(items);

    Assertions.assertEquals(3, itemService.getSellableItems(page, pageSize).size());
  }

  @Test
  public void test_not_sellable() {
    int page = 0;
    Assertions.assertThrows(IllegalArgumentException.class,
      () -> itemService.getSellableItems(page, 2));
  }

  @Test
  public void item_not_found() {

    when(itemRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(IllegalArgumentException.class, () -> itemService.getItem(1L));
  }

  @Test
  public void test_created_items() {
    ItemCreation itemCreation = new ItemCreation("Test Item", "Test Description",
      "https://test.com/photo.jpg", 10, 20);
    UserEntity user = new UserEntity("user", "password", 100);
    String token = "Bearer abc123";
    when(jwtTokenGenerator.getUsernameFromJWT(token.substring(7))).thenReturn("user");
    when(userRepository.findUserByUsername("user")).thenReturn(Optional.of(user));

    Item result = itemService.createSellableItem(itemCreation, token);

    Assertions.assertEquals(itemCreation.getName(), result.getName());
    Assertions.assertEquals(itemCreation.getDescription(), result.getDescription());
    Assertions.assertEquals(itemCreation.getPhotoUrl(), result.getPhotoUrl());
    Assertions.assertEquals(itemCreation.getStartingPrice(), result.getStartingPrice());
    Assertions.assertEquals(itemCreation.getPurchasePrice(), result.getPurchasePrice());
  }
}