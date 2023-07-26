package com.example.walletlink;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Models.User;
import com.example.walletlink.Services.Implementation.AccountServiceImpl;
import com.example.walletlink.Services.Implementation.TransactionServiceImpl;
import com.example.walletlink.Services.Implementation.UserServiceImpl;
import com.example.walletlink.Services.Implementation.WalletSeviceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class WalletLinkApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    WalletSeviceImpl walletSevice;
    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    AccountServiceImpl accountService ;

    @Test
    void contextLoads() {
     /*   User u = new User("Oussema", "Mabrouki", "07220650", "92652461", "mabrouki552@gmail.com", "123456");
        User u2 = new User("Jawher", "Limem", "12345678", "90050177", "jawher@gmail.com", "123456");
        User u3 = new User("Globale","Globale","00000000","00000000","00000000","00000000");
        userService.register(u);
        userService.register(u2);
        userService.register(u3);
        Account global = new Account("9861450092652461","00000000",100.0);
        Account a = new Account(accountService.generateRib(),"07220650",1500.5);
        Account a2 = new Account(accountService.generateRib(),"12345678",500.0);
        accountService.createAccount(a);
        accountService.createAccount(a2);
        accountService.createAccount(global);
        walletSevice.fundWallet("07220650",100, Objects.requireNonNull(walletSevice.getWalletDetails("07220650").getBody()).getRefWallet());
       */
        walletSevice.peerToPeer(Objects.requireNonNull(walletSevice.getWalletDetails("07220650").getBody()).getRefWallet(),Objects.requireNonNull(walletSevice.getWalletDetails("12345678").getBody()).getRefWallet(),50);

    }

}
