package com.example.walletlink.Services;

import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface WalletService {

     String  ReferenceGenerator();
    ResponseEntity<String> fundWallet(String cin, float cash);
    ResponseEntity<String> CashWithdraw(float cash);
    void QR_generator() throws IOException, WriterException;

}
