package com.example.walletlink.Services;

import java.util.Map;

public interface TransactionService {
    Map<String,Object> initTransaction(String sender, String receiver, double amount);
    Map<String,Object> getTransactions(String cin);

}
