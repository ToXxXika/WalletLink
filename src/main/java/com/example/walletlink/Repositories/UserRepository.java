package com.example.walletlink.Repositories;

import com.example.walletlink.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository   extends JpaRepository<User,String> {
}
