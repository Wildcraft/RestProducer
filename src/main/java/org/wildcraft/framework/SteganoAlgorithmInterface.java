package org.wildcraft.framework;

import java.io.File;

/**
 * Created by Narendran Solai on 6/15/15.
 */
public interface SteganoAlgorithmInterface {

    public File hideData(File file, String data);
    public String unHideDate(File file);
}
