package com.example.walletlink.Services;

import com.example.walletlink.Models.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
  ResponseEntity<String> register(User u );
  Principal login(Principal p);
}
