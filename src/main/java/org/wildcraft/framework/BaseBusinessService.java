package org.wildcraft.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.wildcraft.businessobjects.User;

import java.io.File;

/**
 * Created by deepeekamai on 6/19/15.
 */
public class BaseBusinessService {

    @Autowired
    SecureObject secureObject;

    public <T extends BaseBusinessObject> File getSecureFile(T  baseBusinessObject){
        secureObject.putObject(baseBusinessObject);
        File file = secureObject.getSecureFile();
        return file;
    }

    public <T extends BaseBusinessObject> T getEntity(File file) {
        return (T)secureObject.getObject(file);
    }

}
