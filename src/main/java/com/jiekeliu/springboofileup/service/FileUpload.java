package com.jiekeliu.springboofileup.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/27 0027 17:40
 * @Description:
 */
public interface FileUpload {
    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
