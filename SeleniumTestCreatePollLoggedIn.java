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

public class SeleniumTestCreatePollLoggedIn {

	@Test 
	public void createPoll() {   

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
	    passwordElement.sendKeys("12345");
		
	    signinform.submit(); 

		browser.get("http://localhost:8085/KnowItAll/createPollPage.jsp"); 
	    
	    WebElement createPollForm = browser.findElement(By.name("createPollForm"));
	    WebElement title = browser.findElement(By.name("title"));
	    WebElement tags = browser.findElement(By.name("tags"));
	    WebElement option1 = browser.findElement(By.name("option1"));
		WebElement option2 = browser.findElement(By.name("option2"));
		WebElement date = browser.findElement(By.name("date"));
		WebElement image = browser.findElement(By.name("image"));
		
		title.sendKeys("which greeting is better?"); 
		tags.sendKeys("hi, hey, hello, hola"); 
		option1.sendKeys("hi");
		option2.sendKeys("hey");
	    date.sendKeys("11/18/2018");
	    image.sendKeys("http://archtopmusictherapy.com/wp-content/uploads/2016/09/Wave-Your-Hand.png");
		
	    createPollForm.submit(); 

		//browser.close();

		}

	

}
