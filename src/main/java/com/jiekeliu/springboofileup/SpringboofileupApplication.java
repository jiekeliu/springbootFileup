package com.jiekeliu.springboofileup;

import com.jiekeliu.springboofileup.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
        FileStorageProperties.class
})
@SpringBootApplication
public class SpringboofileupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringboofileupApplication.class, args);
    }

}
