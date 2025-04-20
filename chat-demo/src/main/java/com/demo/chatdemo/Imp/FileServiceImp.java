package com.demo.chatdemo.Imp;

import com.demo.chatdemo.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImp implements FileService {
    @Override
    public String saveFile(MultipartFile file, String senderId) {
        return "";
    }
}
