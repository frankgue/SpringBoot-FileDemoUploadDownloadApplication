package com.gkfcsolution.filedemouploaddownload.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created on 2025 at 16:50
 * File: null.java
 * Project: FileDemoUploadDownload
 *
 * @author Frank GUEKENG
 * @date 09/12/2025
 * @time 16:50
 */
public interface FileService {
    void uploadFile(MultipartFile uploadFile) throws IOException;

    Resource downloadFile(String filename);
}
