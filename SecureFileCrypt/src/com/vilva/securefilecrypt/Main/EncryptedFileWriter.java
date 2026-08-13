package com.vilva.securefilecrypt;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptedFileWriter {

    public void write(
            String outputFile,
            EncryptedFileFormat encryptedFile)
            throws IOException {

        try (
            DataOutputStream output =
                    new DataOutputStream(
                            new FileOutputStream(outputFile))
        ) {

            output.writeUTF(
                    EncryptedFileFormat.MAGIC
            );

            output.writeByte(
                    EncryptedFileFormat.VERSION
            );

            output.write(
                    encryptedFile.getSalt()
            );

            output.write(
                    encryptedFile.getIv()
            );

            output.write(
                    encryptedFile.getCiphertext()
            );
        }
    }
}