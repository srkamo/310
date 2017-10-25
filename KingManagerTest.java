package Business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import Database.Database;

public class KingManagerTest {

	@SuppressWarnings("null")
	@Test
	public void testGetCurUser() {
		String email = "bmuller@usc.edu";
		String password = "7c6a180b36896a0a8c02787eeafb0e4c";
		String fName ="Bob";
		String lName = "Muller";
		
		User user = new User(email, password, fName, lName);
		
		KingManager testKing= new KingManager();
		testKing.setCurUser(user);
	    User userActual = testKing.getCurUser(); 
	    assertEquals(user, userActual);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testSearch() {

	KingManager testKing = new KingManager();
		int expectedNumPizza = 5; 

		int actualNumPizza = testKing.search("pizza").size();
		
	    assertEquals(expectedNumPizza, actualNumPizza);
	}

	@SuppressWarnings("null")
	@Test
	public void testGetCurrUserActions() {
		int expectedNumActions= 0;
		String email = "bmuller@usc.edu";
		String password = "7c6a180b36896a0a8c02787eeafb0e4c";
		String fName ="Bob";
		String lName = "Muller";
		
		User user = new User(email, password, fName, lName);
		
		KingManager testKing= new KingManager();
		testKing.setCurUser(user);
		int actualNumActions = testKing.getCurrUserActions().size();
		
		assertEquals(expectedNumActions, actualNumActions);
		
	}
	
	@SuppressWarnings("null")
	@Test
	public void testUserRatedOrVoted(){

		KingManager testKing= new KingManager();
		Boolean actualResult=testKing.userRatedOrVoted(2,"bmuller@usc.edu");
		
		Boolean expectedResult=false;
	
		assertEquals(expectedResult, actualResult);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testgetUser(){

		String expectedUser = "bmuller@usc.edu";
		
		KingManager testKing= new KingManager();
		
	    String actualUser = testKing.getUser("bmuller@usc.edu").getEmail(); 
	    
	    assertEquals(expectedUser, actualUser);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testAddUser(){
		String email = "newuser@usc.edu";
		String password = "hello";
		String fName ="John";
		String lName = "Doe";
		
		User user = new User(email, password, fName, lName);
		
		KingManager testKing= new KingManager();
		testKing.addUser(user);
		
		String actualUser=testKing.getUser("newuser@usc.edu").getEmail();
	    assertEquals(email, actualUser);	
	}
	
	@SuppressWarnings("null")
	@Test
	public void testGetEntity(){
		KingManager testKing = new KingManager();
		testKing.search("pizza");
		
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 

		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity expectedEntity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
		String actualEntityName=testKing.getEntity(2).getTitle();

	    assertEquals(expectedEntity.getTitle(), actualEntityName);	
			
	}
	
	@SuppressWarnings("null")
	@Test
	public void testAddEntity(){
		
		KingManager testKing = new KingManager();
		
		String title1 = "Papa Johns";
		int id1 = 13; 
		String description = "Real ingredients. Papa johns."; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("peyton manning"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
		
		testKing.addEntity(entity);
		
		String actualEntityName= testKing.getEntity(13).getTitle();
		
	    assertEquals(entity.getTitle(), actualEntityName);	
		
	}
}
