package com.jiekeliu.springboofileup.service;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/27 0027 17:59
 * @Description:
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
