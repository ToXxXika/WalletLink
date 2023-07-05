package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.User;
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

    @Override
    public ResponseEntity<String> register(User u) {
        try {
            userRepository.save(u);
            return  new ResponseEntity<>("User Registered", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("User Not Registered",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Principal login(Principal p) {
           return p ;
    }
}
