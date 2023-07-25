package com.example.walletlink.Controllers;

import com.example.walletlink.Services.Implementation.WalletSeviceImpl;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        walletSevice.QR_generator("X", 0.0);
    }
    @PostMapping("/fw")
    public ResponseEntity<String> fundwallet(@RequestParam(name = "cin")String cin, @RequestParam(name="cash")float cash,@RequestParam(name = "walletref") String walletRef){
        return walletSevice.fundWallet(cin,cash,walletRef);
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> p2p(@RequestParam(name = "sender")String sender,@RequestParam(name = "receiver")String receiver,@RequestParam(name = "amount")float amount){
        return walletSevice.peerToPeer(sender,receiver,amount);
    }

}
