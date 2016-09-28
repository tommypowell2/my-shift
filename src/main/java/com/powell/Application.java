package com.powell;

/**
 * Created by tpowell on 9/11/16.
 * -_-
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.powell.controller", "com.powell.service"})
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }

}
