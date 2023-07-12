package com.example.walletlink.Services;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Models.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
  ResponseEntity<String> register(User u );
  String login(Principal p);
  ResponseEntity<String> transferMoney(String cin , float cash);
  ResponseEntity<String> addAccount(Account a);
}
