package com.iamder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iamder.config.DynamoDbConfig;

@SpringBootApplication
public class ImaderRrrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImaderRrrApplication.class, args);
		
	}

}
