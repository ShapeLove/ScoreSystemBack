package com.shape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by HXC on 2018/3/26.
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:spring/*.xml"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
