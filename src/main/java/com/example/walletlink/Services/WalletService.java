package com.example.walletlink.Services;

import org.springframework.http.ResponseEntity;

public interface WalletService {

    String ReferenceGenerator();
    ResponseEntity<String> fundWallet(String cin, float cash);
    ResponseEntity<String> CashWithdraw(float cash);

}
