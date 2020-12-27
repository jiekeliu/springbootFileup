package com.jiekeliu.springboofileup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/27 0027 17:46
 * @Description:
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
