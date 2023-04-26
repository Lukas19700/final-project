package com.example.finalproject;

import com.example.finalproject.models.Item;
import com.example.finalproject.models.UserEntity;
import com.example.finalproject.repositories.ItemRepository;
import com.example.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ItemRepository itemRepository;
@Autowired
  public FinalProjectApplication(UserRepository userRepository,PasswordEncoder passwordEncoder,ItemRepository itemRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.itemRepository = itemRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(FinalProjectApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    UserEntity user = new UserEntity("lukas",passwordEncoder.encode("heslo"),10000);
    userRepository.save(user);
    UserEntity user1 = new UserEntity("mato",passwordEncoder.encode("heslo"),20000);
    userRepository.save(user1);
    Item item = new Item("samsung","galaxy","URL",250,200,user);
    itemRepository.save(item);
  }
}
