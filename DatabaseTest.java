package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Business.Action;
import Business.Entity;
import Business.Poll;
import Business.User;
import Database.Database;

public class DatabaseTest {
	
	//most of these test methods work right after running populate_db.sql
	//the ones that need more setup are specified
	
	@Test
	public void getUser(){
		Database db = new Database();
		User user = db.getUser("mbent@usc.edu");
		String fName = "Morgan";
		String lName = "Bent";
		String email = "mbent@usc.edu";
		
		assertEquals("testing fName", fName, user.getFName());
		assertEquals("testing lName", lName, user.getLName());
		assertEquals("testing email", email, user.getEmail());
		
		user = db.getUser("notAnEmail");
		assertEquals(null, user);
	}
	
	@Test
	public void addUser(){
		Database db = new Database();
		User newUser = new User("email", "password", "fName", "lName");
		db.addUser(newUser);
		
		User user = db.getUser("email");
		assertEquals("testing addUser", newUser.getEmail(), user.getEmail());
		assertEquals("testing addUser", newUser.getFName(), user.getFName());
		assertEquals("testing addUser", newUser.getLName(), user.getLName());
		assertEquals("testing addUser", newUser.getPassword(), user.getPassword());
	}
	
	@Test 
	//have to log into mbent@usc.edu and perform 3 actions first
	public void getUserActions(){
		Database db = new Database();
		ArrayList<Action> userActions = db.getUserActions("mbent@usc.edu");
		assertEquals(3, userActions.size());
	}
	
	@Test
	public void getEntities(){
		Database db = new Database();
		ArrayList<Entity> entities = db.getEntities();
	
		assertEquals(8, entities.size());
	}
	
	@Test
	public void getPolls(){
		Database db = new Database();
		ArrayList<Poll> polls = db.getPolls();
	
		assertEquals(3, polls.size());
	}
	
	@Test 
	public void upVoteEntity(){
		Database db = new Database();
		ArrayList<Entity> entities = db.getEntities();
		
		Entity blazePizza = entities.get(1);
		int ogRating = blazePizza.getRating();
		db.upVoteEntity(3);
		entities = db.getEntities();
		blazePizza = entities.get(1);
		int newRating = blazePizza.getRating();
		
		assertEquals(newRating, ogRating+1);
	}
	
	@Test 
	public void downVoteEntity(){
		Database db = new Database();
		ArrayList<Entity> entities = db.getEntities();
		
		Entity blazePizza = entities.get(1);
		int ogRating = blazePizza.getRating();
		db.downVoteEntity(3);
		entities = db.getEntities();
		blazePizza = entities.get(1);
		int newRating = blazePizza.getRating();
		
		assertEquals(newRating, ogRating-1);
	}
	
	
	@Test
	public void searchForPolls(){
		Database db = new Database();
		ArrayList<Poll> searchPolls = db.searchForPolls("pizza");
		assertEquals(1, searchPolls.size());
	}
	
	
	@Test 
	public void searchForEntities(){
		Database db = new Database();
		ArrayList<Entity> searchEntities = db.searchForEntities("pizza");
		assertEquals(4, searchEntities.size());
	}
	
	
	@Test 
	public void getNumThings(){
		Database db = new Database();
		int numThings = db.getNumThings();
		System.out.println("numThings: " + numThings);
		
		assertEquals(11, numThings);
	}
	
	@Test 
	//have to log into mbent@usc.edu and upVote the following entities first:
	// Pizza Studio, Blaze Pizza, Domino's
	public void userRatedOrVoted(){
		Database db = new Database();
		Boolean ratedPizzaStudio = db.userRatedOrVoted(2, "mbent@usc.edu");
		Boolean ratedBlaze = db.userRatedOrVoted(3, "mbent@usc.edu");
		Boolean ratedDominos = db.userRatedOrVoted(4, "mbent@usc.edu");
		Boolean ratedPizzaHut = db.userRatedOrVoted(5, "mbent@usc.edu");
		
		assertEquals(ratedPizzaStudio, true);
		assertEquals(ratedBlaze, true);
		assertEquals(ratedDominos, true);
		assertEquals(ratedPizzaHut, false);
	}
	
	
}
