package com.seleniumdemo.lambdatest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class seleniumTestInCloud {
	
	RemoteWebDriver driver;
	
	@BeforeTest
	public void setUp()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "Dropdown");
		capabilities.setCapability("name", "Interact with Select");
		capabilities.setCapability("browserName", "Safari");
		capabilities.setCapability("browserVersion", "16.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "sayanbhowmik1995");
		ltOptions.put("accessKey", "R1BAAkjV1udgnsTCLupuZ9aEAJ9P8IsoxEYSFRXBJMzGviWCHT");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("platformName", "MacOS Ventura");
		ltOptions.put("network", true);
		ltOptions.put("project", "Untitled");
		capabilities.setCapability("LT:Options", ltOptions);
		try {
			driver= new RemoteWebDriver(new URL("https://sayanbhowmik1995:R1BAAkjV1udgnsTCLupuZ9aEAJ9P8IsoxEYSFRXBJMzGviWCHT@hub.lambdatest.com/wd/hub"),capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void myTest()
	{
		driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		String title=driver.getTitle();
		System.out.println(title);
		WebElement Input1=driver.findElement(By.id("sum1"));
		String attribute=Input1.getAttribute("placeholder");
		System.out.println("###Placeholder Value: "+attribute);
		Input1.sendKeys("3.2");
		WebElement Input2=driver.findElement(By.id("sum2"));
		Input2.sendKeys("4.1");
		sleep();
		driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
		WebElement result= driver.findElement(By.id("addmessage"));
		System.out.println("#####Result: "+result.getText());
		sleep();
		tearDown();
		
	}
	
	public void myTest1()
	{
		driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		String title=driver.getTitle();
		System.out.println(title);
	}
	
	
	
	public void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
