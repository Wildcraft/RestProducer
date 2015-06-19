package org.wildcraft.crypto.algorithms;

import org.wildcraft.framework.EncryptionAlgorithmInterface;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * AES 128
 */
public class AES128Algorithm {
    
     private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public String encrypt(String Data) {
        String encryptedValue = null;
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            encryptedValue = new BASE64Encoder().encode(encVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return encryptedValue;
    }

    public String decrypt(String encryptedData) {
        String decryptedValue = null;
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            decryptedValue = new String(decValue);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static void main(String[] args) {
        AES128Algorithm aes128Algorithm = new AES128Algorithm();

        String encryptedString = aes128Algorithm.encrypt("My Secret");
        System.out.println("Encypted String : "+ encryptedString);

        String decryptedString = aes128Algorithm.decrypt(encryptedString);
        System.out.println("Decrypted String : "+ decryptedString);
    }


}