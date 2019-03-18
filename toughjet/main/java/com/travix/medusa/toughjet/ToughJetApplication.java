package com.travix.medusa.toughjet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by parisfreire on 11/03/2019.
 */
@ComponentScan("com.travix.medusa.*")
@SpringBootApplication
public class ToughJetApplication{

    public static void main( String[] args )
    {
        SpringApplication.run(ToughJetApplication.class, args);
    }
}
