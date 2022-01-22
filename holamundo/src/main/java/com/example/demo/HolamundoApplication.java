package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HolamundoApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HolamundoApplication.class, args);
		
	}

}