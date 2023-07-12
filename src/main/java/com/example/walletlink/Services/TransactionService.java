package com.example.walletlink.Services;

import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<String> initTransaction(String sender, String receiver, float amount);

}
