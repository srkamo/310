import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumTest {

	@Test
	public void testPollPageScoresAndComments() {
		WebDriver browser;
		
		System.setProperty("webdriver.gecko.driver", "/Users/morganbent/Downloads/geckodriver");
		browser = new FirefoxDriver();
		browser.get("http://localhost:8080/KnowItAll/index.jsp");
		browser.get("http://localhost:8080/KnowItAll/pollPage.jsp?subjectID=1"); 
		
		WebElement scores = browser.findElement(By.id("scores"));
		WebElement comment = browser.findElement(By.id("comment"));

		
		assertTrue((scores.isDisplayed()));
		assertTrue((comment.isDisplayed()));
		browser.close();
	}
	
	@Test
	public void testEntityPageRatingAndComments(){
		WebDriver browser;
		
		System.setProperty("webdriver.gecko.driver", "/Users/morganbent/Downloads/geckodriver");
		browser = new FirefoxDriver();
		browser.get("http://localhost:8080/KnowItAll/index.jsp");
		browser.get("http://localhost:8080/KnowItAll/entityPage.jsp?subjectID=2"); 
		
		WebElement rating = browser.findElement(By.id("rating"));
		WebElement comment = browser.findElement(By.id("comment"));

		
		assertTrue((rating.isDisplayed()));
		assertTrue((comment.isDisplayed()));
		browser.close();
	}
	
	@Test 
	public void testUserCanOnlyVoteOnce(){
		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver", "/Users/morganbent/Downloads/geckodriver");   

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
		
		WebElement submitVoteForm = browser.findElement(By.name("submitVoteForm"));
	    submitVoteForm.submit(); 
	    WebElement submitVoteForm2 = browser.findElement(By.name("submitVoteForm"));
	    submitVoteForm2.submit(); 

	    //check the browser -- there shoud be an error message on the poll page saying that 
	    //the user has already voted on this poll
	}
	
	@Test 
	public void testCreatePollPage(){
		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver", "/Users/morganbent/Downloads/geckodriver");   

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8080/KnowItAll/index.jsp");   
		
		browser.get("http://localhost:8080/KnowItAll/createPollPage.jsp"); 
		
		assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/KnowItAll/createPollPage.jsp"));
		browser.close();
	}
	
	@Test
	public void testUserPageFeedUpdates(){
		WebDriver browser;   

		//Firefox's geckodriver requires you to specify its location.    
		System.setProperty("webdriver.gecko.driver", "/Users/morganbent/Downloads/geckodriver");   

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
		
		WebElement submitVoteForm = browser.findElement(By.name("submitVoteForm"));
	    submitVoteForm.submit(); 
	   
	    browser.get("http://localhost:8080/KnowItAll/userPage.jsp"); 
	    WebElement historyElement = browser.findElement(By.id("userHistory"));
	    
	    assertTrue(historyElement.isDisplayed());

	    //check the browser -- there shoud be an error message on the poll page saying that 
	    //the user has already voted on this poll
	}

}
