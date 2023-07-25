package com.example.walletlink.Services;

import com.example.walletlink.Models.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    String generateRib();
    ResponseEntity<String> createAccount(Account account);

}
