package org.wildcraft.framework;

/**
 * Created by Narendran Solai on 5/15/15.
 */
public interface EncryptionAlgorithmInterface {

    public String encrypt(String data);

    public String decrypt(String cipher);
}
