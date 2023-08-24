package com.example.walletlink.Repositories;

import com.example.walletlink.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("select t from Transaction t where t.sender = ?1 or t.receiver = ?1")
    List<Transaction> findTransactionsByCin(String cin);
}
