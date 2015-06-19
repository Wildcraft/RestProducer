package org.wildcraft.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wildcraft.service.IUserBusinessService;

@Controller
@RequestMapping("/fileUploader")
public class FileUploadController {

    @Autowired
    private IUserBusinessService userBusinessSerivce;

	@RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUserUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleUserUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

    @RequestMapping(value="/download", method=RequestMethod.GET)
    public @ResponseBody File handleGetUserFile() {
        File file = userBusinessSerivce.getUserFile();
        return file;
    }
}
