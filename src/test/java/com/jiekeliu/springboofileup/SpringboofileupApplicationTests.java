package com.jiekeliu.springboofileup;

import com.jiekeliu.springboofileup.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringboofileupApplicationTests {

    @Autowired
    FileService fileService;

    @Test
    void contextLoads() {

        int res_map = fileService.checkFilename("201907121562897618766681.png.jpg");

        System.out.println(res_map);

    }

}
