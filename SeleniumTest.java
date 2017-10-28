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

public class SeleniumTest {

	@Test 
	public void site_header_is_on_home_page() {   

		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Natalie Monger\\Documents\\geckodriver.exe");   

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8085/KnowItAll/index.jsp");   

		browser.get("http://localhost:8085/KnowItAll/login.jsp"); 
		
		/*WebElement signupform = browser.findElement(By.name("signupform"));
		WebElement firstNameElement = browser.findElement(By.name("fName"));
		WebElement lastNameElement = browser.findElement(By.name("lName"));
		WebElement usernameElement = browser.findElement(By.name("email"));
		WebElement passwordElement = browser.findElement(By.name("password"));
		
		firstNameElement.sendKeys("Natalie");
		lastNameElement.sendKeys("Monger");
		usernameElement.sendKeys("mongerr@usc.edu");
	    passwordElement.sendKeys("12345");
	    
	    signupform.submit(); */
	    
	    WebElement signupform = browser.findElement(By.name("signupform"));
	    WebElement firstNameElement = browser.findElement(By.name("fName"));
	    WebElement lastNameElement = browser.findElement(By.name("lName"));
	    WebElement usernameElement = browser.findElement(By.name("email"));
		WebElement passwordElement = browser.findElement(By.name("password"));
		
		firstNameElement.sendKeys("Natalie"); 
		lastNameElement.sendKeys("Monger"); 
		usernameElement.sendKeys("mongerrrrrrrrrrr@usc.edu");
	    passwordElement.sendKeys("12345");
		
	    signupform.submit(); 
	    
	    WebDriverWait wait = new WebDriverWait(browser, 10);
	    WebElement feedElement = wait.until(
	         ExpectedConditions.presenceOfElementLocated(By.name("searchTextBar"))
	        );
	    
	    browser.findElement(By.name("searchTextBar")); 
	    
	    /*// Anticipate web browser response, with an explicit wait
	    WebDriverWait wait = new WebDriverWait(browser, 10);
	    WebElement messageElement = wait.until(
	         ExpectedConditions.presenceOfElementLocated(By.name("searchTextBar"))
	        );
	    
	 // Run a test
	    String message                 = messageElement.getText();
	    String successMsg             = "Welcome to foo. You logged in successfully.";
	    Assert.assertEquals (message, successMsg);
	 
	    // Conclude a test
	    browser.quit();*/
		


		browser.close();

		}

	

}
