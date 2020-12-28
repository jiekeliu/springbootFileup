package com.jiekeliu.springboofileup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/28 0028 11:05
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    private int fid;

    private String fileName;  //文件名称

    private String fileDownloadUri; //下载地址

    private Double fileSize; //文件大小

    private String fileType; //文件类型

    private String fileDes; //文件描述

    private int fileStatus; //是否启用



}
