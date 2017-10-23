package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Business.Action;
import Business.Entity;
import Business.EntityManager;
import Business.Poll;
import Business.User;
import Database.Database;

public class EntityManagerTest {

	@Test 
	public void getEntity(){
		Database db = new Database();
		EntityManager entityManager = new EntityManager();
		entityManager.setEntityList(db.getEntities());
		
		Entity pizzaStudio = entityManager.getEntity(2);
		assertEquals("testing getEntity", "Pizza Studio", pizzaStudio.getTitle());
		assertEquals("testing getEntity", "Pizza in a studio", pizzaStudio.getDescription());
	}
	
	
	@Test 
	public void newRating(){
		Database db = new Database();
		EntityManager entityManager = new EntityManager();
		entityManager.setEntityList(db.getEntities());
		
		int ogRating = entityManager.getEntity(2).getRating();
		entityManager.newRating(2, true);
		int newRating = entityManager.getEntity(2).getRating();
		assertEquals(ogRating+1, newRating);
		
		ogRating = entityManager.getEntity(2).getRating();
		entityManager.newRating(2, false);
		newRating = entityManager.getEntity(2).getRating();
		assertEquals(ogRating-1, newRating);
	}
	
	@Test 
	public void newComment(){
		Database db = new Database();
		EntityManager entityManager = new EntityManager();
		entityManager.setEntityList(db.getEntities());
		
		int ogNumComments = entityManager.getEntity(2).getComments().size();
		entityManager.newComment(2, "mbent@usc.edu", "new comment");
		int newNumComments = entityManager.getEntity(2).getComments().size();
		assertEquals(ogNumComments+1, newNumComments);
	}
	
	
	
	@Test
	public void addView(){
		Database db = new Database();
		EntityManager entityManager = new EntityManager();
		entityManager.setEntityList(db.getEntities());
		
		int ogNumViews = entityManager.getEntity(2).getNumViews();
		entityManager.addView(2);
		int newNumViews = entityManager.getEntity(2).getNumViews();
		assertEquals(ogNumViews+1, newNumViews);
	}
	
}
