package com.example.walletlink.Models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Rib", nullable = false, length = 40)
    private String rib;
    @Basic
    @Column(name = "user_account", nullable = true, length = 8)
    private String userAccount;
    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    private Double balance;
    @OneToOne
    @JoinColumn(name = "user_account", referencedColumnName = "cin" ,insertable = false,updatable = false)
    private User userByUserAccount;
    @OneToMany(mappedBy = "accountBySender")
    private Collection<Transaction> transactionsByRib;
    @OneToMany(mappedBy = "accountByReceiver")
    private Collection<Transaction> transactionsByRib_0;

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (rib != null ? !rib.equals(account.rib) : account.rib != null) return false;
        if (userAccount != null ? !userAccount.equals(account.userAccount) : account.userAccount != null) return false;
        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rib != null ? rib.hashCode() : 0;
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    public User getUserByUserAccount() {
        return userByUserAccount;
    }

    public void setUserByUserAccount(User userByUserAccount) {
        this.userByUserAccount = userByUserAccount;
    }

    public Collection<Transaction> getTransactionsByRib() {
        return transactionsByRib;
    }

    public void setTransactionsByRib(Collection<Transaction> transactionsByRib) {
        this.transactionsByRib = transactionsByRib;
    }

    public Collection<Transaction> getTransactionsByRib_0() {
        return transactionsByRib_0;
    }

    public void setTransactionsByRib_0(Collection<Transaction> transactionsByRib_0) {
        this.transactionsByRib_0 = transactionsByRib_0;
    }
}
