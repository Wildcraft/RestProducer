package org.wildcraft.service;

import org.wildcraft.businessobjects.User;

import java.io.File;

/**
 * Created by Narendran Solai on 5/19/15.
 */
public interface IUserBusinessService {
    File getUserFile();
    User getUser(File file);
}
