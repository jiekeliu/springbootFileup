package com.jiekeliu.springboofileup.controller;

import com.jiekeliu.springboofileup.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: jiekeliu
 * @Date: 2020/12/28 0028 13:41
 * @Description:
 */
@RestController
public class FileController {

    @Autowired
    FileService fileService;


     /*分页请求参数
        page: 1    //页数
        limit: 20  //每页对象数
        sort: false //是否倒序 false=asc,ture=desc
        * */

    @GetMapping("/getFileData")
    @CrossOrigin(origins = "*")
    public Map getFile(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("sort") boolean sort){

        Map res_file = fileService.geFileDataByPage(page, limit, sort);

        return res_file;
    }


    @PostMapping("/delFile")
    @CrossOrigin(origins = "*")
    public Map delFile(@RequestParam("fid") int fid,@RequestParam("fileName") String fileName){

        Map del_res = fileService.delFileById(fid, fileName);

        return del_res;
    }


}
