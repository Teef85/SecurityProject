/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secproject;

import static com.sun.java.accessibility.util.EventID.KEY;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static java.util.Objects.hash;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author HP
 */
//Teef alzulfi 3950489
//Alanoud alakhder 3951671
//Shoog alghamdi 3953163
public class Secproject {

    public static void DES_Encrypt(String key, String input) {
        try {
            File File1 = new File(input);
            File File2 = new File(input.replace(".txt", ".encrypted"));
            Key key1 = new SecretKeySpec(key.getBytes(), "DES");

            Cipher c1 = Cipher.getInstance("DES");
            c1.init(Cipher.ENCRYPT_MODE, key1);
            FileInputStream inputStream = new FileInputStream(File1);
            byte[] inputBytes = new byte[(int) File1.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = c1.doFinal(inputBytes);
            FileOutputStream stream = new FileOutputStream(File2);
            stream.write(outputBytes);

            inputStream.close();
            stream.close();

            System.out.println("Done! File " + File1.getName() + " is Encrypted using DES");
            System.out.println("Output file is " + File2.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void AES_Encrypt(String key, String input) {
        try {
            File File1 = new File(input);
            File File2 = new File(input.replace(".txt", ".encrypted"));
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher c1 = Cipher.getInstance("AES");
            c1.init(Cipher.ENCRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(File1);
            byte[] inputBytes = new byte[(int) File1.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = c1.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(File2);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            System.out.println("Done! File " + File1.getName() + " is encrypted using AES ");
            System.out.println("Output file is " + File2.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void DES_Decrypt(String key, String input) {
        try {
            File File1 = new File(input);
            File File2 = new File(File1.getAbsolutePath().replace(".encrypted", ".decrypted"));
            Key key1 = new SecretKeySpec(key.getBytes(), "DES");

            Cipher c1 = Cipher.getInstance("DES");
            c1.init(Cipher.DECRYPT_MODE, key1);

            FileInputStream inputStream = new FileInputStream(File1);
            byte[] inputBytes = new byte[(int) File1.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = c1.doFinal(inputBytes);

            FileOutputStream stream = new FileOutputStream(File2);
            stream.write(outputBytes);

            inputStream.close();
            stream.close();

            System.out.println("Done! File " + File1.getName() + " is Decrypted using DES");
            System.out.println("Output file is " + File2.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void AES_Decrypt(String key, String input) {
        try {
            File File1 = new File(input);
            File File2 = new File(File1.getAbsolutePath().replace(".encrypted", ".decrypted"));
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher c1 = Cipher.getInstance("AES");
            c1.init(Cipher.DECRYPT_MODE, secretKey);
            FileInputStream inputStream = new FileInputStream(File1);
            byte[] inputBytes = new byte[(int) File1.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = c1.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(File2);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            System.out.println("Done! File " + File1.getName() + " is decrypted using AES");
            System.out.println("Output file is " + File2.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void getCryptoHash(String fileInputPath, String out, String algorithm) throws FileNotFoundException, IOException {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            File fileInput = new File(fileInputPath);
            FileInputStream inputStream = new FileInputStream(fileInput);
            byte[] inputBytes = new byte[(int) fileInput.length()];
            inputStream.read(inputBytes);

            byte[] inputDigest = md.digest(inputBytes);
            // Convert byte array into signum representation

            BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);

            String hashtext = inputDigestBigInt.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            File fileEncryptOut = new File(out);
            FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
            outputStream = new FileOutputStream(fileEncryptOut);
            outputStream.write(inputDigest);
            inputStream.close();
            outputStream.close();
            System.out.println("File successfully encrypted!");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static File Creatfile(String fileName) {
        File file = new File(fileName + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Scanner input = new Scanner(System.in);
        // Secproject hash = new  Secproject());

        while (true) {
            System.out.println("  MAIN MENU  ");
            System.out.println("======================================================");
            System.out.println("what do you need to implement?");
            System.out.println(" 1- Encryption \n 2- Digital Signature \n 3- Exit");
            System.out.println("======================================================");
            System.out.println("Enter your choice: ");
            int in = input.nextInt();
            switch (in) {
                case 1: {
                    System.out.println("======================================================");
                    System.out.println(" 1- Encrypt \n 2- Decrypt \n 3- Back to main menu");
                    System.out.println("======================================================");
                    System.out.println("Enter your choice: ");
                    int in2 = input.nextInt();
                    // break;
                    switch (in2) {//بعد مايختار الانكربت يحدد فايل او فولدر 
                        case 1:
                            System.out.println("======================================================");
                            System.out.println(" 1- File \n 2- Folder ");
                            System.out.println("Enter your choice: ");
                            System.out.println("======================================================");
                            int in3 = input.nextInt();
                            switch (in3) {// يختار الخوارمزية  اختار فايل
                                case 1:

                                    System.out.println(" 1- DES \n 2- AES ");
                                    System.out.println("Enter your choice: ");
                                    System.out.println("======================================================");
                                    int in4 = input.nextInt();
                                    //break;

                                    switch (in4) {//اختار نوعها 
                                        case 1:

                                            System.out.println("give me a file path : ");
                                            System.out.println("======================================================");

                                            String File = input.next();
                                            System.out.print("Enter key : ");
                                            String key = input.next();
                                            DES_Encrypt(key, File);
                                            System.exit(0);
                                            break;

                                        case 2:

                                            System.out.println("give me a file path : ");
                                            System.out.println("======================================================");

                                            String File2 = input.next();
                                            System.out.print("Enter key : ");
                                            String key2 = input.next();
                                            System.out.println("======================================================");
                                            System.out.print("Enter key : ");
                                            AES_Encrypt(key2, File2);
                                            System.exit(0);
                                            break;
                                    }
                                case 2://ادا اختار فولدر يختار النوع  
                                    System.out.println("======================================================");
                                    System.out.println(" 1- DES \n 2- AES ");
                                    System.out.println("Enter your choice: ");

                                    int in5 = input.nextInt();
                                    System.out.println("======================================================");
                                    switch (in5) {
                                        case 1:  //يختار نو التشفير للفولدر   

                                            System.out.println("give me a folder path : ");

                                            String File = input.next();
                                            System.out.print("Enter key : ");

                                            String key = input.next();
                                            System.out.println("======================================================");
                                            File dir = new File(File);
                                            for (File file1 : dir.listFiles()) {
                                                if (file1.getName().endsWith("txt")) {
                                                    DES_Encrypt(key, file1.getAbsolutePath());
                                                }
                                            }
                                            System.exit(0);
                                            break;
                                        case 2:
                                            System.out.println("give me a folder path : ");

                                            String File2 = input.next();

                                            System.out.print("Enter key : ");
                                            String key2 = input.next();
                                            System.out.println("======================================================");
                                            File dir2 = new File(File2);
                                            for (File file : dir2.listFiles()) {
                                                if (file.getName().endsWith("txt")) {
                                                    AES_Encrypt(key2, file.getAbsolutePath());
                                                }
                                            }
                                            System.exit(0);
                                            break;

                                    }

                            }

                        case 2:// يختار ديكربت 
                            System.out.println("======================================================");
                            System.out.println(" 1- File \n 2- Folder ");
                            System.out.println("Enter your choice: ");
                            int in6 = input.nextInt();
                            System.out.println("======================================================");
                            switch (in6) {// اختار فايل يسوي عليه ديكربت لازم يختار الخوارزمية  
                                case 1:
                                    System.out.println("======================================================");
                                    System.out.println(" 1- DES \n 2- AES ");
                                    System.out.println("Enter your choice: ");
                                    int in7 = input.nextInt();
                                    System.out.println("======================================================");
                                    switch (in7) {// اذا اختار الdes 
                                        case 1:
                                            System.out.println("give me a file path : ");

                                            String File = input.next();
                                            System.out.print("Enter key : ");
                                            String key = input.next();
                                            System.out.println("======================================================");
                                            DES_Decrypt(key, File);
                                            System.exit(0);
                                            break;
                                        case 2:

                                            System.out.println("give me a file path : ");

                                            String File2 = input.next();
                                            System.out.print("Enter key : ");
                                            String key2 = input.next();
                                            System.out.println("======================================================");
                                            AES_Decrypt(key2, File2);
                                            System.exit(0);
                                            break;
                                    }
                                case 2: // اختار ديكربت فولدر 
                                    System.out.println("======================================================");
                                    System.out.println(" 1- DES \n 2- AES ");
                                    System.out.println("Enter your choice: ");
                                    int in9 = input.nextInt();
                                    System.out.println("======================================================");
                                    switch (in9) {// اذا اختار الdes 
                                        case 1:
                                            System.out.println("give me a file path : ");

                                            String File = input.next();

                                            System.out.print("Enter key : ");
                                            String key = input.next();
                                            System.out.println("======================================================");
                                            File dir = new File(File);
                                            for (File file : dir.listFiles()) {
                                                if (file.getName().endsWith("encrypted")) {
                                                    DES_Decrypt(key, file.getAbsolutePath());
                                                }
                                            }

                                            System.exit(0);
                                            break;
                                        case 2:

                                            System.out.println("give me a file path : ");

                                            String File2 = input.next();
                                            System.out.print("Enter key : ");
                                            String key2 = input.next();
                                            System.out.println("======================================================");
                                            File dir3 = new File(File2);
                                            for (File file : dir3.listFiles()) {
                                                if (file.getName().endsWith("encrypted")) {
                                                    AES_Decrypt(key2, file.getAbsolutePath());
                                                }
                                            }
                                            System.exit(0);
                                            break;
                                    }
                                case 3:
                                    continue;
                            }

                    }
                }

                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Type your file name :");
                    String fileName = scanner.next();
                    File HashFile = new File(fileName);
                    
                    try {
                        File ciphertext = Creatfile(HashFile.getName());
                        getCryptoHash(HashFile.getAbsolutePath(), ciphertext.getAbsolutePath(), "SHA256");
                        System.out.println("---------------------");
                    } catch (FileNotFoundException ex) {
                        System.out.println("The file dosn't exsit");
                        System.exit(0);
                        break;
                    }

                case 3: {
                    System.exit(0);
                    break;
                }

            }
        }
    }

}

