package com.example.walletlink.Controllers;

import com.example.walletlink.Models.User;
import com.example.walletlink.Services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("*")  //TODO: change it to a specific port
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserServiceImpl userService;



    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @PostMapping("/register")
    public Map<String,Object> register(@RequestParam(name = "email") String mail , @RequestParam(name="mdp") String mdp,@RequestParam(name = "cin") String cin){
        System.out.println("mail : "+mail+" mdp : "+mdp+" cin : "+cin);
        User u = new User(mail,mdp,cin);
        return userService.register(u);
    }


    @PostMapping(value = "/login" )
    public Map<String, Object> login(@RequestParam(name = "email") String mail, @RequestParam(name = "mdp") String password){
        return userService.login(mail,password);
    }
    @PostMapping("/changePassword")
    public Map<String,Object> changePassword(@RequestParam(name = "cin")String cin , @RequestParam(name = "oldPassword")String oldPassword , @RequestParam(name = "newPassword")String newPassword){
        return userService.changePassword(cin, oldPassword, newPassword);
    }
}
