package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Models.Account;
import com.example.walletlink.Models.Wallet;
import com.example.walletlink.Repositories.AccountRepository;
import com.example.walletlink.Repositories.WalletRepository;
import com.example.walletlink.Services.WalletService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Service
public class WalletSeviceImpl implements WalletService {
    private static final String FILE_PATH = "walletreference.txt";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionServiceImpl transactionService;

    private static Set<String> loadExistingNumbers() {
        Set<String> numbers = new HashSet<>();
        try {
            java.nio.file.Files.lines(java.nio.file.Path.of(FILE_PATH)).forEach(numbers::add);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    private static String generateRandomNumber() {
        Random random = new Random();
        int randomValue = random.nextInt(99999);
        String alphabetPositions = getAlphabetPositions();
        return alphabetPositions +String.format("%03d",randomValue);
    }

    private static  String getAlphabetPositions(){
        StringBuilder positions = new StringBuilder();
        String targetString ="UIB";
        for(char c:targetString.toCharArray()){
            int position = ALPHABET.indexOf(Character.toUpperCase(c));
            positions.append(position);
        }
        return positions.toString();
    }

    private static void saveNumberToFile(String number) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            // Append the generated number to the file
            writer.println(number);
            System.out.println("HELLO");
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }
    @Override
    public  String  ReferenceGenerator() {
        //load existing numbers from file
        Set<String> existingNumbers = loadExistingNumbers();
        String randomNumber ;
        do{
            randomNumber = generateRandomNumber();
        }while (existingNumbers.contains(randomNumber));
        saveNumberToFile(randomNumber);
        return randomNumber ;
    }

    //TODO : complete this method 7/12/2023
    // we have 2 cases heree 1- the user is from UIB 2- the user is from another bank
    @Override
    public ResponseEntity<String> fundWallet(String cin, float cash, String walletref) {
        try {
            Account a = accountRepository.findByUserAccount(cin);
            if (a == null) {
                // Todo : here we need to implement external verification for the bank account and apply the same logic
                return new ResponseEntity<>("Transaction Failed", HttpStatus.BAD_REQUEST);
            }
            Wallet w = walletRepository.findById(walletref).get();
            if (Objects.requireNonNull(a).getBalance() > cash) {
                w.setBalance(w.getBalance() + cash);
                transactionService.initTransaction(a.getRib(), "9861450092652461", cash);
                walletRepository.save(w);
                System.out.println("Wallet funded successfully");
                return ResponseEntity.ok("Wallet funded successfully");
            } else {
                return ResponseEntity.ok("Insufficient funds");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Transaction Failed", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<String> peerToPeer(String sender, String receiver, float amount) {
        try {
            Wallet x = walletRepository.findById(sender).get();
            if (x.getBalance() > amount) {
                x.setBalance(x.getBalance() - amount);
                Wallet y = walletRepository.findById(receiver).get();
                y.setBalance(y.getBalance() + amount);
                walletRepository.save(x);
                Account a = accountRepository.findByUserAccount(x.getUserWallet());
               //TODO : fix me 26/07/2023
                transactionService.initTransaction(a.getRib(), "9861450092652461", amount);
                walletRepository.save(y);
                return ResponseEntity.ok("Wallet Transaction done successfully");
            } else {
                System.out.println("Insufficient funds");
                return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Wallet Transaction Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Wallet> getWalletDetails(String cin) {
        try{

            return new ResponseEntity<>(walletRepository.findByUserWallet(cin),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> CashWithdraw(float cash) {
        return null;
    }

    @Override
    public void QR_generator(String walletref, double cash) throws IOException, WriterException {
        String filepath = "qr.png";
        int size = 125;
        String fileType = "png";
        File qrFile = new File((filepath));
        createQrImage(qrFile, walletref, cash, size, fileType);
        System.out.println("DONE");
    }

    //this function is used to transfer money from one wallet to another
    // it will get the sender wallet reference and the receiver wallet reference and the amount to be transferred


    private static void createQrImage(File qrFile, String walletref, double cash, int size, String fileType)
            throws WriterException, IOException {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintmap = new Hashtable<>();
        hintmap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(walletref + cash, BarcodeFormat.QR_CODE, size, size, hintmap);
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        graphics.setColor(Color.RED);
        for(int i =0;i<matrixWidth;i++){
            for(int j=0;j<matrixWidth;j++){
                if(byteMatrix.get(i,j)){
                    graphics.fillRect(i,j,1,1);
                }
            }
        }


        ImageIO.write(image,fileType,qrFile);
    }
}
