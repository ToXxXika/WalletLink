package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Models.User;
import com.example.walletlink.Models.Wallet;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Repositories.UserRepository;
import com.example.walletlink.Repositories.WalletRepository;
import com.example.walletlink.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    WalletSeviceImpl walletService ;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public ResponseEntity<String> register(User u) {
        try {
            userRepository.save(u);
            Wallet w = new Wallet();
            w.setBalance(0.0);
            w.setUserWallet(u.getCin());
            w.setRefWallet(walletService.ReferenceGenerator());
            walletRepository.save(w);
            return  new ResponseEntity<>("User & Wallet Registered", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("User Not Registered",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String login(Principal p) {
           return p.getName() ;
    }

    @Override
    public ResponseEntity<String> transferMoney(String cin, float cash) {
        return null;
    }

    @Override
    public ResponseEntity<String> addAccount(Account a) {
        try{
            //todo : implement SMT verification here
            accountRepository.save(a);
            return new ResponseEntity<>("Account Details Added",HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Account Details  Not Added",HttpStatus.BAD_REQUEST);
        }
    }
}
