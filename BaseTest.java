package com.ibm.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.pages.UserPage;
import com.ibm.utilities.ExcelUtil;
import com.ibm.utilities.PropertiesFileHandler;

//jars would be in the link
// https://drive.google.com/open?id=1Oa7AzFN8nQapvatjRToU_bwDgpf18QKI
public class BaseTest extends ExcelUtil {
	WebDriverWait wait;
	WebDriver driver;
	@Test
	public void testCase8() throws IOException, InterruptedException {
		FileInputStream file = new FileInputStream("./TestData/data.properties");
		Properties prop = new Properties();
		prop.load(file);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		UserPage user = new UserPage(driver, wait);
		
		WebElement loginEle=driver.findElement(By.linkText("Login"));
		loginEle.click();
		
		user.userName(username);
		user.enterPassword(password);
		user.clickOnLogin();
		Thread.sleep(3000);
		WebElement categoryEle=driver.findElement(By.cssSelector("#categories-menu > ul.menu > li.menu-item > span.click-categories.flaticon-bars"));
		categoryEle.click();
	
		WebElement fruitsEle=driver.findElement(By.linkText("Fruits"));
		fruitsEle.click();
		
		WebElement firstCartEle=driver.findElement(By.id("addtocart_cartbtn234"));
		firstCartEle.click();
		
		WebElement secondCartEle=driver.findElement(By.id("addtocart_cartbtn231"));
		secondCartEle.click();
		
		WebElement cartEle=driver.findElement(By.cssSelector("div.cart-menu > a > i.flaticon-commerce"));
		cartEle.click();
		
		WebElement goToCartEle=driver.findElement(By.linkText("Go To Cart"));
		goToCartEle.click();
		
		WebElement deleteCartEle=driver.findElement(By.linkText("×"));
		deleteCartEle.click();

		WebElement updateCartEle=driver.findElement(By.cssSelector("button.button.button-check-out"));
		updateCartEle.click();
		
		WebElement cartCheckEle=driver.findElement(By.id("bigCart"));
		cartCheckEle.click();
		
		Assert.assertTrue(driver.findElement(By.partialLinkText("1")).isDisplayed());
}
}
