package com.example.walletlink.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Transaction {
    @Basic
    @Column(name = "sender")
    private String sender;
    @Basic
    @Column(name = "receiver")
    private String receiver;
    @Basic
    @Column(name = "amount")
    private double amount;
    @Id
    @Column(name = "ref_trans")
    private String refTrans;
    @Basic
    @Column(name = "date_trans")
    private Timestamp dateTrans;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "Rib", nullable = false,insertable = false,updatable = false)
    private Account accountBySender;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "Rib", nullable = false,insertable = false,updatable = false)
    private Account accountByReceiver;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(refTrans, that.refTrans) && Objects.equals(dateTrans, that.dateTrans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, amount, refTrans, dateTrans);
    }

    public Account getAccountBySender() {
        return accountBySender;
    }

    public void setAccountBySender(Account accountBySender) {
        this.accountBySender = accountBySender;
    }

    public Account getAccountByReceiver() {
        return accountByReceiver;
    }

    public void setAccountByReceiver(Account accountByReceiver) {
        this.accountByReceiver = accountByReceiver;
    }
}
