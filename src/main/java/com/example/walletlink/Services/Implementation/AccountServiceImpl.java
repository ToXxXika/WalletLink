package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public String generateRib() {
        String ribNumber = "012";
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            ribNumber += String.valueOf(random.nextInt(10));
        }
        return ribNumber;

    }

    @Override
    public Map<String, Object> createAccount(Account account) {
        Map<String,Object> x = new HashMap<>();
        try {
            accountRepository.save(account);
            x.put("account",account);
            x.put("code",200);
            return x;
        } catch (Exception E) {
            System.out.println(E.getMessage());
            x.put("code",500);
            x.put("message",E.getMessage());
            return x;

        }
    }
}
