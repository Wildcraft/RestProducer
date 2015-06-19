package org.wildcraft.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Created by Narendran Solai on 5/19/15.
 */
@Component
public class SecureObject<T extends BaseBusinessObject> {

    @Autowired(required = true)
    @Qualifier("base64")
    private EncryptionAlgorithmInterface encryptionAlgorithmInterface;

    @Autowired(required = true)
    private SteganoAlgorithmInterface steganoAlgorithmInterface;

    @Autowired(required = true)
    private CoverRepoInterface coverRepoInterface;

    private T dataObject;

    private File secureFile;

    public void setSecureFile(File secureFile) {
        this.secureFile = secureFile;
    }

    public File getSecureFile() {

        try {
            String inputString = toString(dataObject);
            System.out.println(dataObject);
            if(T.getDataClassification() == DataClassification.CONFIDENTIAL) {
                inputString = encryptionAlgorithmInterface.encrypt(inputString);
            }
            System.out.println(inputString);
            File file = coverRepoInterface.getCoverFile(inputString);
            this.secureFile = steganoAlgorithmInterface.hideData(file,inputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return secureFile;
    }

    public void putObject(T dataObject) {
        this.dataObject = dataObject;
    }

    public T getObject(File secureFile) {
        String outputString = steganoAlgorithmInterface.unHideDate(secureFile);

        System.out.println(outputString);

        if(T.getDataClassification() == DataClassification.CONFIDENTIAL) {
            outputString = encryptionAlgorithmInterface.decrypt(outputString);
        }

        try {
            this.dataObject = (T) fromString(outputString);
        }
        catch (ClassNotFoundException cne) {
            cne.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataObject;
    }


    /** Read the object from Base64 string. */
    private static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

}
