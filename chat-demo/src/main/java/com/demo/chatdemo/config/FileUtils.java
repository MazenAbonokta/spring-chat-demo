package com.demo.chatdemo.config;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {
    private FileUtils(){}
    public static byte[] getFileBytes(String fileUrl){

        if(StringUtils.isBlank(fileUrl)){

        }
        Path path=new File(fileUrl).toPath();
        try {
            return Files.readAllBytes(path);
        }catch (Exception e){

            log.error("Error while reading file bytes :"+e.getMessage());
            return new byte[0];
        }

    }
}
