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

public class SeleniumSearchEntity {

	@Test 
	public void searchEntity() {   

		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Natalie Monger\\Documents\\geckodriver.exe");   

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8085/KnowItAll/index.jsp");   
		browser.get("http://localhost:8085/KnowItAll/feed.jsp"); 
	    
	    WebElement searchform = browser.findElement(By.name("searchform"));
	    WebElement searchtextbar = browser.findElement(By.name("searchTextBar"));
		
		searchtextbar.sendKeys("blaze");
		
	    searchform.submit(); 
	    
	    
	    WebDriverWait wait = new WebDriverWait(browser, 10);
	    WebElement feedElement = wait.until(
	         ExpectedConditions.presenceOfElementLocated(By.name("titleHeading"))
	        );
	    
	    WebElement entityText = browser.findElement(By.name("titleHeading"));
	    
	    String entityTextString = entityText.getText(); 
	    System.out.println("entity text: " + entityTextString); 
	    
		


		//browser.close();

		}

	

}
