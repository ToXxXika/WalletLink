package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Transaction;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Repositories.TransactionRepository;
import com.example.walletlink.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Map<String,Object> initTransaction(String sender, String receiver, double amount) {
        Map<String,Object> map = new HashMap<>();
        try {
            // here we need to update the sender and receiver accounts
            Transaction t = new Transaction();
            t.setRefTrans("TR"+System.currentTimeMillis());
            t.setDateTrans(new Timestamp(System.currentTimeMillis()));
            t.setSender(sender);
            t.setReceiver(receiver);
            t.setAmount(amount);
            transactionRepository.save(t);
            System.out.println( accountRepository.SubstractFromBalance(sender, amount));
            System.out.println(accountRepository.AddToBalance(receiver, amount));
              map.put("status", 200);
                map.put("message", "Transaction Done");
                map.put("transaction", t);
                return map;
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", "Transaction Failed");
            return map;
        }

    }

    @Override
    public Map<String,Object> getTransactions(String cin) {
        Map<String,Object> map = new HashMap<>();

        try {
            List<Transaction> transactions = transactionRepository.findTransactionsByCin(accountRepository.findByUserAccount(cin).getRib());
            map.put("status", 200);
            map.put("message", "Transactions Found");
            map.put("transactions", transactions);
            map.put("count", transactions.size());
            return map;
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", "Transactions Not Found");
            map.put("count", 0);
            return map;
        }
    }
}