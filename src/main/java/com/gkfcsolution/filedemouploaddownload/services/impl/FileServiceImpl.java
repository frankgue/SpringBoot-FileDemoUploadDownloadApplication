package com.gkfcsolution.filedemouploaddownload.services.impl;

import com.gkfcsolution.filedemouploaddownload.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created on 2025 at 16:50
 * File: FileServiceImpl.java.java
 * Project: FileDemoUploadDownload
 *
 * @author Frank GUEKENG
 * @date 09/12/2025
 * @time 16:50
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${project.upload.dir}")
    private String uploadDir;

    @Override
    public void uploadFile(MultipartFile uploadFile) throws IOException {

        File folder = new File(uploadDir);

        // Si le dossier n'existe pas, on le crée
        if (!folder.exists()) {
            folder.mkdirs();
            log.info("Upload folder created at {}", folder.getAbsolutePath());
        }

        File file = new File(folder, uploadFile.getOriginalFilename());

        if (file.exists()) {
            log.warn("File already exists: {}", file.getAbsolutePath());
        }

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
            stream.write(uploadFile.getBytes());
        }

        log.info("File saved successfully: {}", file.getAbsolutePath());
    }

    @Override
    public Resource downloadFile(String filename) {
        return new FileSystemResource(uploadDir + "/" + filename);
    }
}
