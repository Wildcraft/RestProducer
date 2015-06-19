package org.wildcraft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wildcraft.businessobjects.User;
import org.wildcraft.service.IUserBusinessService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

/**
 * Created by Narendran Solai on 5/16/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserBusinessService userBusinessSerivce;

    @RequestMapping(value="/receiveUser", method= RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/receiveUser", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName){
        String name = "user at"+ LocalDateTime.now();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("/home/deepeekamai/Pictures/archieve",fileName)));
                stream.write(bytes);
                stream.close();
                File userfile = new File("/home/deepeekamai/Pictures/archieve/", fileName);
                User user = userBusinessSerivce.getUser(userfile);
                System.out.println("Name: "+user.getName());
                return "You successfully uploaded " + fileName + "!";
            } catch (Exception e) {
                e.printStackTrace();
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + fileName + " because the file was empty.";
        }
    }

    @RequestMapping(value="/sendUser", method=RequestMethod.GET)
    public @ResponseBody File handleGetUserFile() {
        File file = userBusinessSerivce.getUserFile();
        return file;
    }

}
