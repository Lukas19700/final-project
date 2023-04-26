package com.example.finalproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.finalproject.models.UserEntity;
import com.example.finalproject.repositories.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;
  private UserServiceImpl userService;

  @Test
  public void test_positive_get_user_by_username() {
    String username = "john";
    UserEntity user = new UserEntity();
    user.setUsername(username);
    Mockito.when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));
    userService = new UserServiceImpl(userRepository);

    UserEntity result = userService.getUserByUsername(username);

    assertEquals(user, result);
  }
}