package com.jiekeliu.springboofileup.service;

import com.jiekeliu.springboofileup.pojo.File;

import java.util.Map;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/28 0028 13:03
 * @Description:
 */
public interface FileService {

    //    分页查询
    Map geFileDataByPage(int page, int pageSize, boolean sort);

    //    添加
    Map addFile(File file);

    //    删除
    Map delFileById(int fid , String fileName);

    //    名称匹配
    int checkFilename(String name);


}
