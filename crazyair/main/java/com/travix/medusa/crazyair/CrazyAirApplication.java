package com.travix.medusa.crazyair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by parisfreire on 11/03/2019.
 */
@ComponentScan("com.travix.medusa.*")
@SpringBootApplication
public class CrazyAirApplication {

    public static void main( String[] args ) {
        SpringApplication.run(CrazyAirApplication.class, args);
    }
}
