package com.demo.chatdemo.Imp;

import com.demo.chatdemo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class FileServiceImp implements FileService {

    @Value("${application.file.uploads.media-output.path}")
    private String mediaOutputPath;
    @Override
    public String saveFile(MultipartFile file, String senderId) {

        String fileUploadSubPath="users"+ File.separator+senderId;
        return uploadFile(file,fileUploadSubPath);
    }

    private String uploadFile(MultipartFile sourceFile, String fileUploadSubPath) {
        String finalUploadFile=fileUploadSubPath+File.separator+sourceFile.getOriginalFilename();
        File destinationFile=new File(fileUploadSubPath);
        if(!destinationFile.exists()){
           boolean fileCreated= destinationFile.mkdirs();

           if(!fileCreated){
               log.error("Error while creating directory for uploaded files");
           }
        }

        String fileExtension=getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath=fileUploadSubPath+ File.separator+System.currentTimeMillis()+fileExtension;
        try {
         /*   Path path=Path.of(targetFilePath);
            Files.write(path,sourceFile.getBytes());*/

            sourceFile.transferTo(new File(targetFilePath));

            log.info("File Saved To {}",targetFilePath);
        } catch (Exception e) {
            log.error("Error while uploading file :"+e.getMessage());
        }
        return finalUploadFile;
    }

    private String getFileExtension(String originalFilename) {
        if(originalFilename==null){
            return null;
        }
        return originalFilename.substring(originalFilename.lastIndexOf(".")+1);
    }
}
