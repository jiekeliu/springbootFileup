package com.jiekeliu.springboofileup.service;

import com.jiekeliu.springboofileup.config.FileStorageProperties;
import com.jiekeliu.springboofileup.mapper.FileMapper;
import com.jiekeliu.springboofileup.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/28 0028 13:06
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    private final Path fileStorageLocation;

    public FileServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create directory where the uploaded files will be stored");
        }
    }

    @Autowired
    FileMapper fileMapper;

    @Override
    public Map geFileDataByPage(int page, int pageSize, boolean sort) {
        HashMap<String, Object> responseInfo = new HashMap<>();
        int fileCount = fileMapper.getFileCount();
        boolean expage = (fileCount/pageSize+1)>=page?true:false;
        if (expage){
            int start = (page-1)*pageSize;
            List<File> data;
            if (sort){
                data = fileMapper.getFileDesc(start, pageSize);
            }else {
                data = fileMapper.getFileAse(start, pageSize);
            }
            responseInfo.put("code",20000);
            responseInfo.put("status","ok");
            responseInfo.put("info","请求成功");
            responseInfo.put("page",page);
            responseInfo.put("pageSize",data.size());
            responseInfo.put("total",fileCount);
            responseInfo.put("data",data);
            return responseInfo;
        }else {
            responseInfo.put("code",20000);
            responseInfo.put("status","error");
            responseInfo.put("info","请求失败，查询的数据超出范围");
            responseInfo.put("page",page);
            responseInfo.put("pageSize",0);
            responseInfo.put("total",0);
            return responseInfo;
        }

    }

    @Override
    public int checkFilename(String name) {
        int count = fileMapper.checkFilename(name);
        return count;
    }

    @Override
    public Map addFile(File file) {

        HashMap<String, Object> responseInfo = new HashMap<>();

        int add_res = fileMapper.addFile(file);
        if (add_res == 1){
            responseInfo.put("code",20000);
            responseInfo.put("status","ok");
            responseInfo.put("info","添加成功");
            responseInfo.put("文件名",file.getFileName());
            responseInfo.put("文件连接",file.getFileDownloadUri());
            responseInfo.put("文件类型",file.getFileType());
            responseInfo.put("文件大小",file.getFileSize());
            return responseInfo;
        }else {
            responseInfo.put("code",20000);
            responseInfo.put("status","error");
            responseInfo.put("info","添加失败");
            return responseInfo;
        }
    }

    @Override
    public Map delFileById(int fid , String fileName) {
        HashMap<String, Object> responseInfo = new HashMap<>();
        if(fid <=0 ){
            responseInfo.put("code",20000);
            responseInfo.put("status","error");
            responseInfo.put("info","删除失败，对象序号错误");
            return responseInfo;
        }

        //        删除本地文件
        String filePath = this.fileStorageLocation.resolve(fileName).normalize().toString();

        System.out.println(filePath);

        java.io.File fileProject = new java.io.File(filePath);
        if (fileProject.exists()){//文件是否存在
            fileProject.delete();//删除文件
        }else {
            responseInfo.put("code",20000);
            responseInfo.put("status","error");
            responseInfo.put("info","删除失败, 文件未找到");
            return responseInfo;
        }


        //        删除数据库信息

        int del_res = fileMapper.delFileById(fid);

        if (del_res == 1){
            responseInfo.put("code",20000);
            responseInfo.put("status","ok");
            responseInfo.put("info","删除成功");
            return responseInfo;
        }else {
            responseInfo.put("code",20000);
            responseInfo.put("status","error");
            responseInfo.put("info","删除失败");
            return responseInfo;
        }
    }
}
