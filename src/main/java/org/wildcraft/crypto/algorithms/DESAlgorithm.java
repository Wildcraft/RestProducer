package org.wildcraft.crypto.algorithms;

import org.wildcraft.framework.EncryptionAlgorithmInterface;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * DES
 */
public class DESAlgorithm {

    public DESAlgorithm(Key key) {
        this.key = key;
    }

    private static final String ALGO = "DES";
    private Key key;


    public String encrypt(String Data) {
        String encryptedValue = null;
        try {
            Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, key);

            byte [] encVal = c.doFinal(Data.getBytes());

            encryptedValue = new BASE64Encoder().encode(encVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return encryptedValue;
    }

    public String decrypt(String encryptedData) {
        String decryptedValue = null;
        try {
            Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] dec = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue = c.doFinal(dec);
            decryptedValue = new String(decValue);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return decryptedValue;
    }
    private static Key generateKey() {
        Key key = null;
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance(ALGO);
             key = keygenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static void main(String[] args) {
        Key key = DESAlgorithm.generateKey();
        DESAlgorithm desAlgorithm = new DESAlgorithm(key);

        String encryptedString = desAlgorithm.encrypt("My Secret");
        System.out.println("Encypted String : "+ encryptedString);

        String decryptedString = desAlgorithm.decrypt(encryptedString);
        System.out.println("Decrypted String : "+ decryptedString);
    }
}