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

public class SeleniumTestCreateEntityNotLoggedIn {

	@Test 
	public void createPoll() {   

		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Natalie Monger\\Documents\\geckodriver.exe");   

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8085/KnowItAll/index.jsp");   

		browser.get("http://localhost:8085/KnowItAll/createEntityPage.jsp"); 
	    
	    WebElement createEntityForm = browser.findElement(By.name("createEntityForm"));
	    WebElement title = browser.findElement(By.name("title"));
	    WebElement description = browser.findElement(By.name("description"));
	    WebElement tags = browser.findElement(By.name("tags"));
		WebElement date = browser.findElement(By.name("date"));
		WebElement image = browser.findElement(By.name("image"));
		
		title.sendKeys("rate a hello"); 
		description.sendKeys("hi this is a description");
		tags.sendKeys("hi, hey, hello, hola"); 
	    date.sendKeys("11/18/2018");
	    image.sendKeys("http://archtopmusictherapy.com/wp-content/uploads/2016/09/Wave-Your-Hand.png");
		
	    createEntityForm.submit(); 
	    
	    browser.findElement(By.name("errorBox"));


		browser.close();

		}

	

}
