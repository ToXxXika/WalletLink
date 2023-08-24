package com.example.walletlink.Controllers;

import com.example.walletlink.Models.Transaction;
import com.example.walletlink.Services.Implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class TransactionController
{
    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/get")
    public Map<String,Object> getTransactions(final String cin) {
        return this.transactionService.getTransactions(cin);
    }

}
