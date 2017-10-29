	@Test 
	public void voteOnEntityNotLoggedIn() {   

		WebDriver browser;   
   
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Saboodi\\Desktop\\geckodriver.exe");   

		browser = new FirefoxDriver();    
		browser.get("http://localhost:8080/KnowItAll/index.jsp"); 
		browser.get("http://localhost:8080/KnowItAll/entityPage.jsp?subjectID=1");
		
		// click on browser.findElement(By.cssSelector("#thumbsupdiv > a:nth-child(1)"))
		// then check for browser.findElement(By.cssSelector("h4.info-text:nth-child(2) > center:nth-child(1)"))
		// it should be there
		
		browser.close();
		}
