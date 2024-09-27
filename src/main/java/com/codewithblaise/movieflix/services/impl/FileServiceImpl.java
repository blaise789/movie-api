package com.codewithblaise.movieflix.services.impl;

import com.codewithblaise.movieflix.services.FileService;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl  implements FileService {


    @Override
    public String updloadFile(String path, MultipartFile file) throws IOException {
        String fileName=file.getName();
        String filePath=path+ File.separator+fileName;
        System.out.println(filePath);
File f=new File(filePath);
        if (f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));



        return fileName;
    }

    @Override
    public InputStream getFileSource(String path, String name) throws FileNotFoundException {
        String filePath=path+File.separator+name;
        return new FileInputStream(filePath);
    }
}
