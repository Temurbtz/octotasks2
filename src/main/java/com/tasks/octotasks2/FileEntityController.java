package com.tasks.octotasks2;

import jakarta.servlet.ServletContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

@RestController
public class FileEntityController {
    @Autowired
    private FileService fileService;
    private static String absolutePath = "/Users/Timur/IdeaProjects/uploaded/";
    @PostMapping("/upload")
    public FileEntityResponse uploadLocal(@RequestParam("file")MultipartFile file) throws IOException {
        UUID uuid=UUID.randomUUID();
        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(absolutePath+uuid+file.getOriginalFilename());
            System.out.println(path);
            Files.write(path, data);
            FileEntityResponse response = new FileEntityResponse();
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(uuid+file.getOriginalFilename())
                    .toUriString();
            response.setDownloadUri(downloadUri);
            response.setUploadStatus(true);
            response.setMessage("File Uploaded Successfully!");
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) throws IOException {
        Path path = Paths.get(absolutePath+id);
        fileService.populateFile(path);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= "+id)
                .body(resource);
    }

}


