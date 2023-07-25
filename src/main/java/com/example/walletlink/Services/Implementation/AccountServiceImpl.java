package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<String> createAccount(Account account) {
        try {
            accountRepository.save(account);
            return new ResponseEntity<>("Account Created", org.springframework.http.HttpStatus.OK);
        } catch (Exception E) {
            System.out.println(E.getMessage());
            return new ResponseEntity<>("Account Not Created", org.springframework.http.HttpStatus.BAD_REQUEST);

        }
    }
}
