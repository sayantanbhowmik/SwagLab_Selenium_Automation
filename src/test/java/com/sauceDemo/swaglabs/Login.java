package com.sauceDemo.swaglabs;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Login {
	WebElement userName;
	WebDriver driver;
	
	public String user1 ="standard_user";
//	public String user2 ="locked_out_user";
//	public String user3 ="problem_user";
//	public String user4 ="performance_glitch_user";
	public String user=user1;
	public String product1Name="//div[contains(text(),'Backpack')]";
	public String productDescription="//div[@class='inventory_details_desc large_size']";
	public String product1Price = "//div[@class='inventory_details_price']";                                                                                                           
	public String addedCartSymbol = "//span[@class='shopping_cart_badge']";
	public String removeBtn = "//*[starts-with(@id,'remove')]";
	public String yourCart = "//div[@id='header_container']//span[@class='title']";
	public String quantityLabel = "//div[@class='cart_quantity_label']";
	public String descriptionLabel = "//div[@class='cart_desc_label']";
	public String prodDescriptionCartPage = "//div[contains(@class,'inventory_item_desc')]";
	public String prodPriceCartPage = "//div[@class='inventory_item_price']";
	public String yourInformationLabel = "//*[contains(text(),'Checkout: Your Information')]";
	public String overviewPageTitle = "//*[contains(text(),'Checkout: Overview')]";
	public String paymentInformationLabel = "//div[contains(text(),'Payment Information')]";
	public String sauceCard = "//div[contains(text(),'SauceCard')]";
	public String shippingInformationLabel = "//div[contains(text(),'Shipping Information')]";
	public String orderTotalPriceLabel = "//div[contains(text(),'Price Total')]";
	public String itemPrice = "//div[contains(text(),'Item total')]";
	public String taxPrice = "//div[contains(text(),'Tax')]";
	public String totalPrice = "//div[contains(text(),'Total:')]";
	public String checkoutCompleteLabel = "//span[contains(text(),'Checkout: Complete!')]";
	public String orderSuccessGreentick = "//*[@id='checkout_complete_container']/img";
	public String orderSuccessMsg = "//h2[contains(text(),'Thank you for your order!')]";
	public String orderStatus = "//div[contains(text(),'Your order has been dispatched')]";
	
	@Test
	public void login() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("Starting logintest..");
		String url="https://www.saucedemo.com/";
		driver.get(url);
		System.out.println("Page is opened!!");
		sleep(5000);
		
		String websiteLogo=driver.findElement(By.className("login_logo")).getText();
		if(websiteLogo.equalsIgnoreCase("Swag Labs"))
		{
			System.out.println("Swag Labs Logo is available in the Login screen...");
		}
		
		userName =driver.findElement(By.id("user-name"));
		
		System.out.println("User name is "+user);
		switch(user)
		{
			case "standard_user":  //For this user it will do successful login
			{
				userName.sendKeys(user);
				sleep(3000);
				sendPwd_LoginBtnClick();
				verifyLoginSuccess();
				productDetails();
				addToCart();
				verifyCart();
				checkout();
				logout();
				break;
			}
			case "locked_out_user":
			{
				
				break;
			}
			case "problem_user":
			{
				
				break;
			}
			case "performance_glitch_user":
			{
				
				break;
			}
			
			
		}
		
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void browserClose() {
		driver.quit();	
	}
	public void scrollDown() { //To scroll down the web page at the bottom of the page.
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}
	public void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void explicitWait(String xpath,Duration time)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public void differentUserVerification(String user)
	{
		
	}

	public void sendPwd_LoginBtnClick() { //sending password in password textbox field & clicking onLogin button to logged into the site
		WebElement passowrd = driver.findElement(By.id("password"));
		passowrd.sendKeys("secret_sauce");
		sleep(3000);
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		sleep(5000);
	}
	public void verifyLoginSuccess() //Verify that the user is successfully logged in by checking for the presence of the Swag Labs logo and  profile Menu option at the top left corner of the page.
	{
		sleep(3000);
		if(driver.findElement(By.className("app_logo")).isDisplayed())
		{
			System.out.println("Swag Lab logo is visible in the product screen!!");
			if(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed()) {
				System.out.println("Profile Menu is visible,Hence it is successful login!!");
			}
		}
		
	}
	public void productDetails() //Select a product from the list of available products,On the product details page confirm that the product's name, description, and price are displayed correctly. 
	{
		if(driver.findElement(By.xpath(product1Name)).isDisplayed())
		{
			
			driver.findElement(By.xpath(product1Name)).click();
			sleep(10000);
			System.out.println("Clicking on First Product  "+driver.findElement(By.xpath(product1Name)).getText());
			explicitWait(product1Name,Duration.ofSeconds(10)); 	
			
			boolean prodDesc= driver.findElement(By.xpath(productDescription)).isDisplayed();
			Assert.assertEquals(prodDesc, true);  
			
			System.out.println("Price of First Product : "+driver.findElement(By.xpath(product1Price)).getText());
			
			}
	}
	public void addToCart() { //Click the "Add to Cart" button to add the selected product to the cart and to verify item is added check the number of item  is added to the cart symbol
		
		if(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed())
		{
			System.out.println("Add to cart option is present.");
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		}
		sleep(3000);
		driver.findElement(By.xpath(addedCartSymbol)).isDisplayed();
		System.out.println("Number of added product in cart is now "+driver.findElement(By.xpath(addedCartSymbol)).getText()); 
		boolean flag=driver.findElement(By.xpath(removeBtn)).isDisplayed();
		if(flag)
		{
			System.out.println("Remove button is visible in the product details page after adding a product in the cart.");
		}
	}
	public void verifyCart() {
		
		if(driver.findElement(By.className("shopping_cart_link")).isDisplayed())
		{
			System.out.println("Shopping cart symbol is present.");
			driver.findElement(By.className("shopping_cart_link")).click();
			if(driver.findElement(By.xpath(yourCart)).isDisplayed())
				System.out.println("We are in Cart page...");
			driver.findElement(By.xpath(quantityLabel)).isDisplayed();
			driver.findElement(By.xpath(descriptionLabel)).isDisplayed();
			if(driver.findElement(By.xpath(product1Name)).isDisplayed())
			{
				boolean prodDesc= driver.findElement(By.xpath(prodDescriptionCartPage)).isDisplayed();
				Assert.assertEquals(prodDesc, true);
				System.out.println("Price of First Product in cart page : "+driver.findElement(By.xpath(prodPriceCartPage)).getText());
			}
			
		}
	}
	public void checkout() {
		
		if(driver.findElement(By.id("checkout")).isDisplayed())
		{
			
			driver.findElement(By.id("checkout")).click();
			sleep(2000);
		}
		boolean flag= driver.findElement(By.xpath(yourInformationLabel)).isDisplayed();
		Assert.assertEquals(flag, true);
		if(flag)
		{
			
			WebElement firstName= driver.findElement(By.id("first-name"));
			firstName.sendKeys("Ram");
			sleep(2000);
			
			WebElement secondName= driver.findElement(By.id("last-name"));
			secondName.sendKeys("Lakshman");
			sleep(2000);
			
			WebElement postalCode= driver.findElement(By.id("postal-code"));
			postalCode.sendKeys("456987");
			sleep(2000);
			
			WebElement continueBtn= driver.findElement(By.id("continue"));
			continueBtn.submit();
			sleep(5000);
		}
		
		boolean flag1= driver.findElement(By.xpath(overviewPageTitle)).isDisplayed();
		Assert.assertEquals(flag1, true);
		
		driver.findElement(By.xpath(product1Name)).isDisplayed();
		driver.findElement(By.xpath(paymentInformationLabel)).isEnabled();  
		if(driver.findElement(By.xpath(sauceCard)).isDisplayed())
			System.out.println("Sauce Card number : "+driver.findElement(By.xpath(sauceCard)).getText());
		driver.findElement(By.xpath(shippingInformationLabel)).isEnabled();
		
		scrollDown();
		//sleep(10000);
		
		if(driver.findElement(By.xpath(orderTotalPriceLabel)).isDisplayed()) {
			WebElement orderedItemPrice = driver.findElement(By.xpath(itemPrice));
			String price1=getPriceInDigits(orderedItemPrice);
			System.out.println("Item actual price in Overview screen : "+price1);
			sleep(5000);
			
			WebElement itemTaxPrice = driver.findElement(By.xpath(taxPrice));
			String price2=getPriceInDigits(itemTaxPrice);
			System.out.println("Item's tax price in Overview screen : "+price2);
			sleep(5000);
			
			WebElement itemTotalPrice = driver.findElement(By.xpath(totalPrice));
			String price3=getPriceInDigits(itemTotalPrice);
			System.out.println("Item's total price in Overview screen : "+price3);
			
		}
		if(driver.findElement(By.id("finish")).isDisplayed())
			driver.findElement(By.id("finish")).click();
		sleep(5000); 
		
		boolean flag2= driver.findElement(By.xpath(checkoutCompleteLabel)).isDisplayed();
		Assert.assertEquals(flag2, true);
		
		Assert.assertEquals(driver.findElement(By.xpath(orderSuccessGreentick)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(orderSuccessMsg)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(orderStatus)).isDisplayed(), true);
		
		sleep(10000);
		
		if(driver.findElement(By.id("back-to-products")).isDisplayed())
			driver.findElement(By.id("back-to-products")).click();
		sleep(5000); 
		
		if(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed()) {
			System.out.println("We are in product page");
			driver.findElement(By.id("react-burger-menu-btn")).click();
			sleep(5000); 
			
		}
		
   }
	public void logout()
	{
		Assert.assertEquals(driver.findElement(By.id("logout_sidebar_link")).isDisplayed(), true);
		if(driver.findElement(By.id("logout_sidebar_link")).isDisplayed())
			driver.findElement(By.id("logout_sidebar_link")).click();
		sleep(5000); 
		String websiteLogo1=driver.findElement(By.className("login_logo")).getText();
		if(websiteLogo1.equalsIgnoreCase("Swag Labs"))
		{
			System.out.println("We have successfully logged out.Swag Labs Logo is available in the Login screen...");
		}
	}
	public String getPriceInDigits(WebElement price)
	{
		String priceInValue=price.getText();
		String x[]=priceInValue.split(": ");
		String p = null;
		for(int i=0;i<x.length;i++) {
			if(x[i].contains("$"))
			{
				p=x[i];
			}
		}
		return p;
	}
	
	
	

}
