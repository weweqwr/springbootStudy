package com.yuntu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class YuntuApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuntuApplication.class, args);
    }

}
