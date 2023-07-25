package com.example.walletlink.Models;

import jakarta.persistence.*;

@Entity
public class Wallet {
    @Id
    @Column(name = "ref_wallet", nullable = false, length = 255)
    private String refWallet;
    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    private Double balance;
    @Basic
    @Column(name = "user_wallet", nullable = true, length = 8)
    private String userWallet;
    @OneToOne
    @JoinColumn(name = "user_wallet", referencedColumnName = "cin",updatable = false,insertable = false)
    private User userByUserWallet;

    public String getRefWallet() {
        return refWallet;
    }

    public void setRefWallet(String refWallet) {
        this.refWallet = refWallet;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(String userWallet) {
        this.userWallet = userWallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wallet wallet = (Wallet) o;

        if (refWallet != null ? !refWallet.equals(wallet.refWallet) : wallet.refWallet != null) return false;
        if (balance != null ? !balance.equals(wallet.balance) : wallet.balance != null) return false;
        if (userWallet != null ? !userWallet.equals(wallet.userWallet) : wallet.userWallet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refWallet != null ? refWallet.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (userWallet != null ? userWallet.hashCode() : 0);
        return result;
    }

    public User getUserByUserWallet() {
        return userByUserWallet;
    }

    public void setUserByUserWallet(User userByUserWallet) {
        this.userByUserWallet = userByUserWallet;
    }
}
