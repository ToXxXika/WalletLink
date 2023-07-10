package com.example.walletlink.Controllers;

import com.example.walletlink.Services.Implementation.WalletSeviceImpl;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/wallet")
@CrossOrigin("*")
@RestController
public class WalletController {

    @Autowired
    WalletSeviceImpl walletSevice ;

    /* this function is used only for testing purposes */
    @GetMapping("/generate")
    public ResponseEntity<String> generate(){
        return new ResponseEntity<>(walletSevice.ReferenceGenerator(), HttpStatus.OK);
    }
    @GetMapping("/qr")
    public void qr() throws IOException, WriterException {
        walletSevice.QR_generator();
    }

}
