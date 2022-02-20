package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests {

	public static void Tests (String[] args) {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://www.wikipdia.es");
			Thread.sleep(5000);	
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		driver.quit();
		}
	}
}
