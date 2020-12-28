package com.jiekeliu.springboofileup.mapper;

import com.jiekeliu.springboofileup.pojo.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/28 0028 12:40
 * @Description:
 */

@Mapper
@Repository
public interface FileMapper {

    //   分页查询数据
    List<File> getFileAse(int start, int pageSize);

    //    查询所有数据(倒序)
    List<File> getFileDesc(int start, int pageSize);

    //    添加
    int addFile(File file);

    //    删除
    int delFileById(int fid);

    //    查询数据总量
    int getFileCount();


    //    名称匹配
    int checkFilename(String name);

}
