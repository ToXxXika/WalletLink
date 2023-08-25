package com.example.walletlink.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class User {
    public User(String nom, String prenom, String cin, String telephone, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.mdp = mdp;
    }
    public User(String mail,String password,String cin){
        this.email=mail;
        this.mdp=password;
        this.cin=cin;
    }
    public User(String email,String password){
        this.email=email;
        this.mdp=password;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 255)
    private String nom;
    @Basic
    @Column(name = "prenom", nullable = true, length = 255)
    private String prenom;
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
    @JsonManagedReference
    @OneToOne(mappedBy = "userByUserAccount")
    private Account accountsByCin;
    @JsonManagedReference
    @OneToOne(mappedBy = "userByUserWallet")
    private Wallet walletsByCin;



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
