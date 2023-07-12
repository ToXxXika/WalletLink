package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Transaction;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Repositories.TransactionRepository;
import com.example.walletlink.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseEntity<String> initTransaction(String sender, String receiver, float amount) {
        try {
            // here we need to update the sender and receiver accounts
            Transaction t = new Transaction();
            t.setSender(sender);
            t.setReceiver(receiver);
            t.setAmount(amount);
            transactionRepository.save(t);
            accountRepository.SubstractFromBalance(sender, amount);
            accountRepository.AddToBalance(receiver, amount);
            return new ResponseEntity<>("Transaction Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Transaction Failed", HttpStatus.BAD_REQUEST);
        }

    }
}
