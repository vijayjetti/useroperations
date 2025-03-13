package com.vijay.learning.useroperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserOperationsApplication {

	private static final Logger log = LoggerFactory.getLogger(UserOperationsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserOperationsApplication.class, args);
		log.info("UserOperations Application Started successfully !!!");
	}
}