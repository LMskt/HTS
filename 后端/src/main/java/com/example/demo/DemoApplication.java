package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//入口文件
@SpringBootApplication
public class DemoApplication {
//通过调用Run，将业务委托给了Spring Boot 的SpringApplication 类。
// SpringApplication将引导用户的应用启动并相应地启动被自动配置的Tomcat Web 服务器。
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
