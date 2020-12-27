package com.jiekeliu.springboofileup.controller;

import com.jiekeliu.springboofileup.service.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Auther: jiekeliu
 * @Date: 2020/12/27 0027 14:45
 * @Description:
 */

@RestController
public class FileUpAndDown {


    private static final Logger logger = LoggerFactory.getLogger(FileUpAndDown.class);

    private final FileUpload storageService;


    public FileUpAndDown(FileUpload storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = storageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName).toUriString();

        return new UploadFileResponse(fileName,fileDownloadUri,file.getContentType(),file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
        return Arrays.stream(files).map(this::uploadFile).collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = storageService.loadFileAsResource(fileName);

        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            logger.info("Could not determine file type");
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    /**
     * 实现文件上传
     * */
    @PostMapping("fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return "false";
        }

        System.out.println(file);
        //获取上传文件
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);


        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
        //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/

        try {
            file.transferTo(upload); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }





}
