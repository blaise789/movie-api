package com.codewithblaise.movieflix.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String updloadFile(String path, MultipartFile file) throws IOException;
    InputStream  getFileSource(String path,String name) throws FileNotFoundException;

}
