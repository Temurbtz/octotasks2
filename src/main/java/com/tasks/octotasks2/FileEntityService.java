package com.tasks.octotasks2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileEntityService implements IFileEntityService{
    @Autowired
    private FileEntityRepository fileEntityRepository;
    @Override
    public FileEntity upload(MultipartFile file) {
        FileEntity  fileEntity = new FileEntity();
        try {
            fileEntity.setFileData(file.getBytes());
            fileEntity.setFileName(file.getOriginalFilename());
            FileEntity fileEntityToRet = fileEntityRepository.save(fileEntity);
            return fileEntityToRet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FileEntity download(String fileId) {
        FileEntity uploadedFileToRet = fileEntityRepository.getOne(fileId);
        return uploadedFileToRet;
    }
}
