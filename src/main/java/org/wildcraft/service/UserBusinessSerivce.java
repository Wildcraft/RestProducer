package org.wildcraft.service;

import org.springframework.stereotype.Component;
import org.wildcraft.businessobjects.User;
import org.wildcraft.framework.BaseBusinessService;
import org.wildcraft.framework.DataClassification;
import org.wildcraft.framework.SecureObject;

import java.io.File;

/**
 * Created by Narendran Solai on 5/19/15.
 */
@Component
public class UserBusinessSerivce extends BaseBusinessService implements IUserBusinessService {

    public File getUserFile() {

        User user = new User();
        user.setName("Ramu");
        user.setAge(26L);
        user.setEmail("narendran.ss@gmail.com");
        User.setDataClassification(DataClassification.CONFIDENTIAL);
        System.out.println(User.getDataClassification());

        File file = getSecureFile(user);
        return file;
    }

    public User getUser(File file) {
        User user = getEntity(file);
        return user;
    }
}
