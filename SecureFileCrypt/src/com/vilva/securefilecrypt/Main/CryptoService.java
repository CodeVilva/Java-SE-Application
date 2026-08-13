package com.vilva.securefilecrypt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author vilva
 */
public class CryptoService {
    
    
    public SecretKey generateAESKey() throws NoSuchAlgorithmException {
    
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        
        keyGenerator.init(256);
        
        return keyGenerator.generateKey();
    
    };
    public byte[] encrypt(byte[] data, SecretKey key, byte[] iv) throws Exception{
        
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
        
        cipher.init(ENCRYPT_MODE, key, parameterSpec);
        
        return cipher.doFinal(data);
    };

    public byte[] decrypt(
            byte[] ciphertext,
            SecretKey key,
            byte[] iv)
            throws Exception {

        Cipher cipher =
                Cipher.getInstance(
                        "AES/GCM/NoPadding"
                );

        GCMParameterSpec parameterSpec =
                new GCMParameterSpec(
                        128,
                        iv
                );

        cipher.init(
                Cipher.DECRYPT_MODE,
                key,
                parameterSpec
        );

        return cipher.doFinal(ciphertext);
    }

    public byte[] generateIV(){
        
        byte[] iv = new byte[EncryptedFileFormat.IV_LENGTH];
        
        SecureRandom secureRondom = new SecureRandom();
        
        secureRondom.nextBytes(iv);
        
        return iv;
    };
    
    public byte[] generateSalt() {

        byte[] salt =
                new byte[EncryptedFileFormat.SALT_LENGTH];

        SecureRandom secureRandom =
                new SecureRandom();

        secureRandom.nextBytes(salt);

        return salt;
    }
    
    public SecretKey deriveKey(String password, byte[] salt) throws Exception{
        
        int iterations = 600_000;
        int keyLength = 256;
        
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                iterations,
                keyLength
                );
        
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        
        byte[] keyBytes = factory
                .generateSecret(spec)
                .getEncoded();
                
        return new SecretKeySpec(keyBytes, "AES");
                
    }
}
