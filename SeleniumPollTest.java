package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class SeleniumPollTest {
	@Test 
	public void testUserMustBeLoggedIn(){
		WebDriver browser;   

		//Firefox's gecko-driver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver","/Users/jasonwitherspoon/Desktop/geckodriver");    

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8080/KnowItAll/index.jsp");   
		
		browser.get("http://localhost:8080/KnowItAll/login.jsp"); 
	  
		browser.get("http://localhost:8080/KnowItAll/pollPage.jsp?subjectID=11"); 
		
		WebElement submitVoteForm = browser.findElement(By.className("iradio_square-yellow"));
	    submitVoteForm.submit();  

	    //check the browser -- there should be an error message on the poll page saying that 
	    //the user has already voted on this poll
	}

	
	@Test 
	public void testLoggedInPollVote() {
		WebDriver browser;   
		System.setProperty("webdriver.gecko.driver","/Users/jasonwitherspoon/Desktop/geckodriver");    
		
		browser = new FirefoxDriver();    
		browser.get("http://localhost:8080/KnowItAll/index.jsp");   
		
		browser.get("http://localhost:8080/KnowItAll/login.jsp"); 
		
    		WebElement signinform = browser.findElement(By.name("signinform"));
    		WebElement usernameElement = browser.findElement(By.name("l_email"));
		WebElement passwordElement = browser.findElement(By.name("l_password"));
	
		usernameElement.sendKeys("mbent@usc.edu");
    		passwordElement.sendKeys("password3");
	
    		signinform.submit(); 
    		
    		browser.get("http://localhost:8080/KnowItAll/pollPage.jsp?subjectID=11"); 

    		WebElement submitVoteForm = browser.findElement(By.className("iradio_square-yellow"));
    	    submitVoteForm.submit(); 
	}

}
