package com.example.walletlink.Controllers;

import com.example.walletlink.Models.User;
import com.example.walletlink.Services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")  //TODO: change it to a specific port
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User u){
        return userService.register(u);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(Principal p){
        if(userService.login(p).isEmpty()){
            return new ResponseEntity<>("User is not found ",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("User found",HttpStatus.OK);
        }
    }
}
