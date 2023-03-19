package com.pf_nxsp_myfood.backend.domain.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Controller
@NoArgsConstructor
public class FileUpload {
    @Autowired
    private Environment env;

    public Map<String, Object> saveFile(String folderName, MultipartFile file) throws IOException {
        Map<String, Object> returnObj = new HashMap<>();
        try {
            // Folder Default Route
            String uploadDir = env.getProperty("file.upload-dir");

            // Create Folder with the name where image will be save
            File directory = new File(uploadDir + folderName);

            // Change file names
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = FilenameUtils.getExtension(originalFilename);
            String newFileName = String.format("%s_%s.%s", IdGenerator.generateWithLength(10), System.currentTimeMillis(), extension.toLowerCase());

            Path path = Paths.get(uploadDir + folderName + "/" + newFileName);
            File dest = path.toFile();
    
            // Checks if directory exists, if not create it
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    returnObj.put("statusCode", 400);
                    returnObj.put("message", "Error while trying create the directory");
                    return returnObj;
                }
            }
            
            // Transfers file info to file on the folder
            file.transferTo(dest.toPath());
            
            returnObj.put("statusCode", 200);
            returnObj.put("path", String.format("images/%s/%s", folderName, newFileName));
            
            return returnObj;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            returnObj.put("statusCode", 400);
            returnObj.put("message", e.getMessage());
            return returnObj;
        }
    }

    // NOTE: If finally need updateFile it will make an petitio to BD to gets the path of file and delete it to create new one;
    // public Map<String, Object> updateFile(String folderName, MultipartFile file) throws IOException { }
}