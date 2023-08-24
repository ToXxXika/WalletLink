package com.example.walletlink.Services;

import com.example.walletlink.Models.Account;

import java.util.Map;

public interface AccountService {
    String generateRib();
    Map<String,Object> createAccount(Account account);

}
