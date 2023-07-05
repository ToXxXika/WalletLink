package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Services.WalletService;
import org.springframework.http.ResponseEntity;

public class WalletSeviceImpl implements WalletService {
    @Override
    public String ReferenceGenerator() {
        return null;
    }

    @Override
    public ResponseEntity<String> fundWallet(String cin, float cash) {
        return null;
    }

    @Override
    public ResponseEntity<String> CashWithdraw(float cash) {
        return null;
    }
}
