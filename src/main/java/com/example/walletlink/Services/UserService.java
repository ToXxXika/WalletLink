package com.example.walletlink.Services;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Models.User;

import java.util.Map;

public interface UserService {
  Map<String,Object> register(User u );
  Map<String, Object> login(String mail, String password);
  Map<String,Object> transferMoney(String cin , float cash);
  Map<String,Object> addAccount(Account a);
  Map<String,Object> changePassword(String cin , String oldPassword , String newPassword);
}
