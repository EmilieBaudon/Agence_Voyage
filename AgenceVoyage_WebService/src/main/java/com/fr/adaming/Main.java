package com.fr.adaming;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Main {

	public static void main(String[] args) {
		final Logger log = Logger.getLogger(Main.class);
		
		try {		
		SpringApplication.run(Main.class, args);}
		
		catch (Exception e){
		log.fatal("the application failed");		
		}
	}

}
