package org.wildcraft.coverfile.algorithm;

import org.springframework.stereotype.Component;
import org.wildcraft.framework.CoverRepoInterface;

import java.io.File;

/**
 * Created by Narendran Solai on 6/15/15.
 */
@Component
public class CoverRepository implements CoverRepoInterface {

    public File getCoverFile(String data) {
        return new File("/home/deepeekamai/Pictures/Apply Custom Segment.png");
    }
}
