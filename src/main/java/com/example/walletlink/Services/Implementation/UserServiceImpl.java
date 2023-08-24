package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Models.User;
import com.example.walletlink.Models.Wallet;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Repositories.UserRepository;
import com.example.walletlink.Repositories.WalletRepository;
import com.example.walletlink.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> register(User u) {
        try {
            userRepository.save(u);
            Wallet w = new Wallet();
            w.setBalance(0.0);
            w.setUserWallet(u.getCin());
            w.setRefWallet(walletService.ReferenceGenerator());
            walletRepository.save(w);
            Map<String, Object> x = new HashMap<>();
            x.put("user", u);
            x.put("code", 200);
            x.put("wallet", w);
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Object> login(String mail, String password) {
        Map<String, Object> x = new HashMap<>();

        try {
            List<User> lst = userRepository.findAll();
            x.put("code", 404);

            for (User u : lst) {
                if (u.getEmail().equals(mail) && u.getMdp().equals(password)) {
                    x.put("user", u);
                    x.put("code", 200);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return x;
    }

    @Override
    public Map<String, Object> transferMoney(String cin, float cash) {
        return null;
    }

    @Override
    public Map<String, Object> addAccount(Account a) {
        try{
            //todo : implement SMT verification here
            accountRepository.save(a);
            Map<String, Object> x = new HashMap<>();
            x.put("account", a);
            x.put("code", 200);
            return x;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Map<String, Object> changePassword(String cin, String oldPassword, String newPassword) {
        Map<String, Object> x = new HashMap<>();
        x.put("code", 400);
        try {
            User u = userRepository.findById(cin).get();
            if (u.getMdp().equals(oldPassword)) {
                u.setMdp(newPassword);
                userRepository.save(u);
                x.put("code", 200);
                x.put("message", "Password changed successfully");

            } else {
                x.put("message", "Wrong password");

            }

        } catch (Exception E) {
            System.out.println(E.getMessage());

        }
        return x;
    }
}
