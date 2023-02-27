package com.tasks.octotasks2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileEntityController {
    @Autowired
    private IFileEntityService fileEntityService;
    @PostMapping("/upload")
    public FileEntityResponse uploadDb(@RequestParam("file") MultipartFile multipartFile)
    {
        FileEntity uploadedFile = fileEntityService.upload(multipartFile);
        FileEntityResponse response = new FileEntityResponse();
        if(uploadedFile!=null){
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(uploadedFile.getFileId())
                    .toUriString();
            response.setDownloadUri(downloadUri);
            response.setFileId(uploadedFile.getFileId());
            response.setUploadStatus(true);
            response.setMessage("File Uploaded Successfully!");
            return response;
        }
        response.setMessage("Something went wrong.");
        return response;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id)
    {
        FileEntity uploadedFileToRet =  fileEntityService.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= "+uploadedFileToRet.getFileName())
                .body(new ByteArrayResource(uploadedFileToRet.getFileData()));
    }

}
