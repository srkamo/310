package Business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Business.PollManager;
import Business.Action;
import Business.Poll;
import Business.User;
import Database.Database;


public class PollManagerTest {
	@Test 
	public void getPoll(){
		Database db = new Database();
		PollManager pollManager = new PollManager();
		pollManager.setPollList(db.getPolls());
		
		Poll bestPizza = pollManager.getPoll(11);
		ArrayList<String> options = new ArrayList();
		options.add("Pizza Studio"); 
		options.add("Blaze Pizza");
		options.add("Dominos");
		options.add("Pizza Hut");
		assertEquals("Best Pizza at USC", bestPizza.getTitle());
		assertEquals(options, bestPizza.getOptions());
	}
	
	
	@Test 
	public void newVote(){
		Database db = new Database();
		PollManager pollManager = new PollManager();
		pollManager.setPollList(db.getPolls());
		
		//need Array list with options
		int pollResult = pollManager.getPoll(11).getPollResults().get("Dominos");
		pollManager.newVote(11, "Dominos"); 
		int newVote = pollManager.getPoll(11).getPollResults().get("Dominos");
		assertEquals(pollResult+1, newVote);
	}

	
	@Test 
	public void newComment(){
		Database db = new Database();
		PollManager pollManager = new PollManager();
		pollManager.setPollList(db.getPolls());
		
		int ogNumComments = pollManager.getPoll(11).getComments().size();
		pollManager.newComment(11, "mbent@usc.edu", "new comment");
		int newNumComments = pollManager.getPoll(11).getComments().size();
		assertEquals(ogNumComments+1, newNumComments);
	}
	
	
	
	@Test
	public void addView(){
		Database db = new Database();
		PollManager pollManager = new PollManager();
		pollManager.setPollList(db.getPolls());
		
		int ogNumViews = pollManager.getPoll(11).getNumViews();
		pollManager.addView(11);
		int newNumViews = pollManager.getPoll(11).getNumViews();
		assertEquals(ogNumViews+1, newNumViews);
	}
		
}
