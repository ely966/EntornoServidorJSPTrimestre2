package com.example.demo;

public class DependantService {
	private  Service1 service1;
	private  Service2 service2;
	//**Sea tipofinal haria que no permita la modificacion por lo tanto solo en construcotr. n setter se quita**//
	public DependantService() {
	//**	super();
	//**	this.service1 = service1;**//
	//**	this.service2 = service2;**//
	}
	//**Setter**//
	
	
	
	
	void doSmth() {
		service1.doSmth();
		service2.doSmth();
	}




	public void setService1(Service1 service1) {
		this.service1 = service1;
	}




	public void setService2(Service2 service2) {
		this.service2 = service2;
	}
	
}
