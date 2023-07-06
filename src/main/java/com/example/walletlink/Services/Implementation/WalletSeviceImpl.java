package com.example.walletlink.Services.Implementation;

import com.example.walletlink.Services.WalletService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

@Service
public class WalletSeviceImpl implements WalletService {
    private static final String FILE_PATH = "walletreference.txt";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static Set<String> loadExistingNumbers(){
        Set<String> numbers = new HashSet<>();
        try{
            java.nio.file.Files.lines(java.nio.file.Path.of(FILE_PATH)).forEach(numbers::add);

        }catch (IOException e){
            e.printStackTrace();
        }
        return numbers;
    }
    private static String generateRandomNumber(){
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

    @Override
    public ResponseEntity<String> fundWallet(String cin, float cash) {
        return null;
    }

    @Override
    public ResponseEntity<String> CashWithdraw(float cash) {
        return null;
    }

    @Override
    public void QR_generator() throws IOException, WriterException {
     String walletref = "4545ss";
     double cash = 50.0;
     String filepath = "qr.png";
     int size = 125 ;
     String fileType = "png";
     File qrFile = new File((filepath));
     createQrImage(qrFile,walletref,cash,size,fileType);
     System.out.println("DONE");
    }
    private static void createQrImage(File qrFile, String walletref, double cash , int size,String fileType)
      throws WriterException,IOException
    {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintmap = new Hashtable<>();
        hintmap.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix  = qrCodeWriter.encode(walletref+cash, BarcodeFormat.QR_CODE,size,size,hintmap);
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth,matrixWidth,BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,matrixWidth,matrixWidth);
        graphics.setColor(Color.RED);
        for(int i =0;i<matrixWidth;i++){
            for(int j=0;j<matrixWidth;j++){
                if(byteMatrix.get(i,j)){
                    graphics.fillRect(i,j,1,1);
                }
            }
        }
        BufferedImage logo= ImageIO.read(new File("C:\\Users\\mabro\\IdeaProjects\\WalletLink\\src\\main\\resources\\static\\uib.jpg"));
        int logoSize = matrixWidth / 4 ;
        int posX = (matrixWidth - logoSize)/2;
        int posY = (matrixWidth - logoSize)/2 ;
        graphics.drawImage(logo,posX,posY,logoSize,logoSize,null);

        ImageIO.write(image,fileType,qrFile);
    }
}
