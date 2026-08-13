package com.vilva.securefilecrypt;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class EncryptedFileReader {

    public EncryptedFileFormat read(
            String inputFile)
            throws IOException {

        try (
            DataInputStream input =
                    new DataInputStream(
                            new FileInputStream(inputFile))
        ) {

            String magic = input.readUTF();

            if (!EncryptedFileFormat.MAGIC.equals(magic)) {
                throw new IOException(
                        "Invalid encrypted file format."
                );
            }

            byte version = input.readByte();

            if (version != EncryptedFileFormat.VERSION) {
                throw new IOException(
                        "Unsupported encrypted file version."
                );
            }

            byte[] salt =
                    new byte[EncryptedFileFormat.SALT_LENGTH];

            input.readFully(salt);

            byte[] iv =
                    new byte[EncryptedFileFormat.IV_LENGTH];

            input.readFully(iv);

            byte[] ciphertext =
                    input.readAllBytes();

            return new EncryptedFileFormat(
                    salt,
                    iv,
                    ciphertext
            );
        }
    }
}