package com.seleniumdemo.GoogleAutoPopulating;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GoogleSearchBar {
	public String googleSearchBar = "//*[@title=\"Search\"]";
	
	@Test
	public void searchBar() {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("Starting logintest..");
		String url="https://www.google.com/";
		driver.get(url);
		System.out.println("Page is opened!!");
		sleep(5000);
		driver.findElement(By.xpath(googleSearchBar)).click();
		driver.findElement(By.xpath(googleSearchBar)).sendKeys("Selenium");
		System.out.println("Selenium is typed in google search bar!!");
		sleep(15000);
		//WebElement list=driver.findElement(By.className("sbct"));
		List<WebElement> rows=driver.findElements(By.tagName("li"));

		for(WebElement elem:rows) {
		//System.out.println(elem.getText());
		if(elem.getText().equalsIgnoreCase("selenium webdriver"))
		 {
			System.out.println("Selenium Webdriver element is captured.");
			elem.click();
			sleep(10000);
			break;
		 }
		}
	}
	
	
	public void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
