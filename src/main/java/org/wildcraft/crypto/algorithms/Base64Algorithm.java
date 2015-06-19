package org.wildcraft.crypto.algorithms;

import org.springframework.stereotype.Component;
import org.wildcraft.framework.EncryptionAlgorithmInterface;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by Narendran Solai on 5/15/15.
 */
@Component("base64")
public class Base64Algorithm implements EncryptionAlgorithmInterface {

    public String encrypt(String inputString) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(
                inputString.getBytes(StandardCharsets.UTF_8));
        System.out.println("ENCRYPTED VALUE :"+encodedString);
        return encodedString;
    }

    public String decrypt(String inputString) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(inputString);
        String decryptedValue = new String(decodedByteArray);
        System.out.println("DECRYPTED VALUE :"+decryptedValue);
        return decryptedValue;
    }

    public static void main(String[] args) {
        EncryptionAlgorithmInterface base64Algorithm = new Base64Algorithm();

        String encryptedString = base64Algorithm.encrypt("My Secret");
        System.out.println("Encypted String : "+ encryptedString);

        String decryptedString = base64Algorithm.decrypt(encryptedString);
        System.out.println("Decrypted String : "+ decryptedString);
    }

}
