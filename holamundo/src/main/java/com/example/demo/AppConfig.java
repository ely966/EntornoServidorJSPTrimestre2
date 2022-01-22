package com.example.demo;

import org.springframework.context.annotation.*;
@Configuration
public class AppConfig {
	@Bean
	public HolaMundo holaMundo() {
		return new HolaMundo();
	}
	@Bean
	public DependantService dependantService() {
		//**return new DependantService(service1(),service2());**//
		DependantService ds= new DependantService();
		ds.setService1(service1());
		ds.setService2(service2());
		return ds;
	}
	@Bean
	public Service1 service1()  {
		return new Service1();
	}
	
	@Bean Service2 service2() {
		return new Service2();
	}
}
