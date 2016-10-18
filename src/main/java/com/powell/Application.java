package com.powell;

/**
 * Created by tpowell on 9/11/16.
 * -_-
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.powell.*", "com.powell.service", "com.powell.dao", "com.powell.security", "com.powell.security.*"})
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }

}
