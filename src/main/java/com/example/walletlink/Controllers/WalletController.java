package com.example.walletlink.Controllers;

import com.example.walletlink.Services.Implementation.WalletSeviceImpl;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RequestMapping("/wallet")
@CrossOrigin("*")
@RestController
public class WalletController {

    @Autowired
    WalletSeviceImpl walletSevice;

    /* this function is used only for testing purposes */
    @GetMapping("/generate")
    public ResponseEntity<String> generate() {
        return new ResponseEntity<>(walletSevice.ReferenceGenerator(), HttpStatus.OK);
    }

    @GetMapping("/qr")
    public void qr() throws IOException, WriterException {
        walletSevice.QR_generator("X", 0.0);
    }

    @PostMapping("/fw")
    public Map<String, Object> fundwallet(@RequestParam(name = "cin") String cin, @RequestParam(name = "cash") float cash, @RequestParam(name = "walletref") String walletRef) {
        return walletSevice.fundWallet(cin, cash, walletRef);
    }

    @PostMapping("/transfer")
    public Map<String, Object> p2p(@RequestParam(name = "sender") String sender, @RequestParam(name = "receiver") String receiver, @RequestParam(name = "amount") String amount) {
        double amount1 = Double.parseDouble(amount);
        return walletSevice.peerToPeer(sender, receiver, amount1);
    }

    //gwd = get wallet details
    @GetMapping("/gwd")
    public Map<String, Object> getwalletDetails(@RequestParam(name = "cin") String cin) {
        return walletSevice.getWalletDetails(cin);
    }

    @GetMapping("/gad")
    public Map<String, Object> getAccountDetails(@RequestParam(name = "cin") String cin) {
        return walletSevice.getAccountDetails(cin);
    }
}
