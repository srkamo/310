import static org.junit.Assert.*;

import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;


import org.junit.Test;

public class SeleniumTestLoginIncorrectPassword {

	@Test 
	public void loginIncorrectPassword() {   

		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Natalie Monger\\Documents\\geckodriver.exe");   

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8085/KnowItAll/index.jsp");   

		browser.get("http://localhost:8085/KnowItAll/login.jsp"); 
	    
	    WebElement signinform = browser.findElement(By.name("signinform"));
	    WebElement usernameElement = browser.findElement(By.name("l_email"));
		WebElement passwordElement = browser.findElement(By.name("l_password"));
		
		usernameElement.sendKeys("monger@usc.edu");
	    passwordElement.sendKeys("123456");
		
	    signinform.submit(); 
	    
	    browser.findElement(By.name("errorBox"));

		browser.close();

		}

	

}
