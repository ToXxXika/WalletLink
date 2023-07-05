package com.example.walletlink.Models;

import jakarta.persistence.*;

@Entity
public class Bank {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ref_bank", nullable = false, length = 255)
    private String refBank;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "address", nullable = true, length = 255)
    private String address;

    public String getRefBank() {
        return refBank;
    }

    public void setRefBank(String refBank) {
        this.refBank = refBank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (refBank != null ? !refBank.equals(bank.refBank) : bank.refBank != null) return false;
        if (name != null ? !name.equals(bank.name) : bank.name != null) return false;
        if (address != null ? !address.equals(bank.address) : bank.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refBank != null ? refBank.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
