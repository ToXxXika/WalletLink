package com.example.walletlink.Models;

import jakarta.persistence.*;

@Entity
public class User {
    @Basic
    @Column(name = "nom", nullable = true, length = 255)
    private String nom;
    @Basic
    @Column(name = "prenom", nullable = true, length = 255)
    private String prenom;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cin", nullable = false, length = 8)
    private String cin;
    @Basic
    @Column(name = "telephone", nullable = true, length = 8)
    private String telephone;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "mdp", nullable = true, length = 255)
    private String mdp;
    @OneToOne(mappedBy = "userByUserAccount")
    private Account accountsByCin;
    @OneToOne(mappedBy = "userByUserWallet")
    private Wallet walletsByCin;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (nom != null ? !nom.equals(user.nom) : user.nom != null) return false;
        if (prenom != null ? !prenom.equals(user.prenom) : user.prenom != null) return false;
        if (cin != null ? !cin.equals(user.cin) : user.cin != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (mdp != null ? !mdp.equals(user.mdp) : user.mdp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (cin != null ? cin.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        return result;
    }

    public Account getAccountsByCin() {
        return accountsByCin;
    }

    public void setAccountsByCin(Account accountsByCin) {
        this.accountsByCin = accountsByCin;
    }

    public Wallet getWalletsByCin() {
        return walletsByCin;
    }

    public void setWalletsByCin(Wallet walletsByCin) {
        this.walletsByCin = walletsByCin;
    }
}
