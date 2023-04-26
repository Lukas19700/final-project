package com.example.finalproject.security;

import com.example.finalproject.models.UserEntity;
import com.example.finalproject.repositories.UserRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findUserByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    List<GrantedAuthority> authorities = Collections.singletonList(
      new SimpleGrantedAuthority("USER"));

    return new User(user.getUsername(), user.getPassword(), authorities);
  }
}
