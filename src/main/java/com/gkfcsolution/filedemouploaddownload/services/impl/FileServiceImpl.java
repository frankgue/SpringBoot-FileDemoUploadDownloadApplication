package com.gkfcsolution.filedemouploaddownload.services.impl;

import com.gkfcsolution.filedemouploaddownload.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public void uploadFile(MultipartFile uploadFile) throws IOException {
        File file = new File(resourceLoader.getResource("classpath:store/").getFile() + "/" + uploadFile.getOriginalFilename());

        if (file.createNewFile()){
            log.info("File is created ! {}", file.getAbsolutePath() );
        } else {
            log.warn("File Already exists.");
        }

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        stream.write(uploadFile.getBytes());
        stream.close();
    }
}
