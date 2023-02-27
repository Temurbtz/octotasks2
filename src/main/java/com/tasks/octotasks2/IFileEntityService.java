package com.tasks.octotasks2;

import org.springframework.web.multipart.MultipartFile;

public interface IFileEntityService {
    public FileEntity upload(MultipartFile file);
    public FileEntity download(String fileId);
}
