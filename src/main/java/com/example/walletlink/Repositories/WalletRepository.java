package com.example.walletlink.Repositories;

import com.example.walletlink.Models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet,String> {
    @Query("SELECT W FROM Wallet W where W.userWallet = ?1")
    Wallet findByUserWallet(String cin);
}
