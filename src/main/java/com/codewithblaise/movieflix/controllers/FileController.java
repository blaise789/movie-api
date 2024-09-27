package com.codewithblaise.movieflix.controllers;

import com.codewithblaise.movieflix.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    @Value("${projects.poster}")
    private  String path;
    @Autowired
    private  FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws IOException {
        String uploadedFileName=fileService.updloadFile(path,file);

        return  ResponseEntity.ok("file"+" "+uploadedFileName+" "+"uploaded successfully");
    }
    @GetMapping("/{fileName}")
    public void serveFileHandler(@PathVariable() String fileName, HttpServletResponse response) throws IOException {
        InputStream file=fileService.getFileSource(path,fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(file,response.getOutputStream());



    }

}
