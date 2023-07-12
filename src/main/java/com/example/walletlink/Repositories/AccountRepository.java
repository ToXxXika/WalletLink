package com.example.walletlink.Repositories;

import com.example.walletlink.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {

    @Modifying
    @Query("update Account a set a.balance = a.balance- ?2 where a.rib = ?1")
    void SubstractFromBalance(String rib, float cash);
    @Modifying
    @Query("update Account a set a.balance = a.balance+ ?2 where a.rib = ?1")
    void AddToBalance(String rib, float cash);
    @Query("select a from Account  a where a.userAccount = ?1")
    Account findByUserAccount(String cin);

}

