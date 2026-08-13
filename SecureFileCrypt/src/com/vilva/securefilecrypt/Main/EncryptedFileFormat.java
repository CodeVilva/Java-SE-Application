 
package com.vilva.securefilecrypt;

public class EncryptedFileFormat {

    public static final String MAGIC = "VSEC";
    public static final byte VERSION = 1;
    
    static final int SALT_LENGTH = 16;
    static final int IV_LENGTH = 12;

    private final byte[] salt;
    private final byte[] iv;
    private final byte[] ciphertext;

    public EncryptedFileFormat(
            byte[] salt,
            byte[] iv,
            byte[] ciphertext) {

        this.salt = salt;
        this.iv = iv;
        this.ciphertext = ciphertext;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getIv() {
        return iv;
    }

    public byte[] getCiphertext() {
        return ciphertext;
    }
}