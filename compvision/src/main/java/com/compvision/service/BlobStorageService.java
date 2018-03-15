package com.compvision.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface BlobStorageService {


    String store(MultipartFile file);
    
    String getBlobUrl(String filename);
    
}
