package com.example.walletlink.Services;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Map;

public interface WalletService {

     String  ReferenceGenerator();
    Map<String,Object> fundWallet(String cin, float cash, String reference);
    Map<String,Object> CashWithdraw(float cash);
    void QR_generator(String x ,double y) throws IOException, WriterException;

    Map<String,Object> peerToPeer(String sender, String receiver, double amount);
    Map<String,Object> getWalletDetails (String cin);

    Map<String,Object> getAccountDetails(String cin);
}
