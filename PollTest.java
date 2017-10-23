package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Test;

import Business.Action;
import Business.CommentAction;
import Business.Entity;
import Business.EntityManager;
import Business.Poll;
import Business.User;
import Database.Database;
import sun.util.cldr.CLDRLocaleDataMetaInfo;

public class PollTest {
	@Test
	public void addTag(){
		ArrayList<String> tags1 = new ArrayList<String>();
		ArrayList<String> options1 = new ArrayList<String>();
		ArrayList<CommentAction> comments1 = new ArrayList<CommentAction>();
		Calendar timeEnd1 = Calendar.getInstance();
		timeEnd1.set(2018, 10, 31);
		HashMap<String, Integer> pollVotes1 = new HashMap<String, Integer>();
		Poll poll = new Poll("title", 1, tags1, options1, 1, comments1, false, timeEnd1, "someURL", pollVotes1);
		
		int ogNumTags = poll.getTags().size();
		poll.addTag("new tag");
		int newNumTags = poll.getTags().size();
		
		assertEquals(newNumTags, ogNumTags+1);
	}
	
	
	@Test
	public void pollClosed(){
		ArrayList<String> tags1 = new ArrayList<String>();
		ArrayList<String> options1 = new ArrayList<String>();
		ArrayList<CommentAction> comments1 = new ArrayList<CommentAction>();
		Calendar timeEnd1 = Calendar.getInstance();
		HashMap<String, Integer> pollVotes1 = new HashMap<String, Integer>();
		//not infinite
		Poll poll1 = new Poll("title", 1, tags1, options1, 1, comments1, false, timeEnd1, "someURL", pollVotes1);
		assertEquals(true, poll1.pollClosed());
		
		//infinite
		Poll poll2 = new Poll("title", 1, tags1, options1, 1, comments1, true, timeEnd1, "someURL", pollVotes1);
		assertEquals(false, poll2.pollClosed());
	}
	
	
	@Test
	public void vote(){
		ArrayList<String> tags1 = new ArrayList<String>();
		ArrayList<String> options1 = new ArrayList<String>();
		ArrayList<CommentAction> comments1 = new ArrayList<CommentAction>();
		Calendar timeEnd1 = Calendar.getInstance();
		HashMap<String, Integer> pollVotes1 = new HashMap<String, Integer>();
		pollVotes1.put("option1", 0);
		//not infinite
		Poll poll = new Poll("title", 1, tags1, options1, 1, comments1, true, timeEnd1, "someURL", pollVotes1);
		
		poll.vote("option1");
		int numVotes = poll.getPollResults().get("option1");
		
		assertEquals(numVotes, 1);
	}
	
	
	@Test
	public void addView(){
		ArrayList<String> tags1 = new ArrayList<String>();
		ArrayList<String> options1 = new ArrayList<String>();
		ArrayList<CommentAction> comments1 = new ArrayList<CommentAction>();
		Calendar timeEnd1 = Calendar.getInstance();
		HashMap<String, Integer> pollVotes1 = new HashMap<String, Integer>();
		//not infinite
		Poll poll = new Poll("title", 1, tags1, options1, 1, comments1, true, timeEnd1, "someURL", pollVotes1);
		
		int ogNumViews = poll.getNumViews();
		poll.addView();
		int newNumViews = poll.getNumViews();
		assertEquals(ogNumViews+1, newNumViews);
	}
	
	
	@Test
	public void addComment(){
		ArrayList<String> tags1 = new ArrayList<String>();
		ArrayList<String> options1 = new ArrayList<String>();
		ArrayList<CommentAction> comments1 = new ArrayList<CommentAction>();
		Calendar timeEnd1 = Calendar.getInstance();
		HashMap<String, Integer> pollVotes1 = new HashMap<String, Integer>();
		//not infinite
		Poll poll = new Poll("title", 1, tags1, options1, 1, comments1, true, timeEnd1, "someURL", pollVotes1);
		
		int ogNumComments = poll.getComments().size();
		CommentAction comment = new CommentAction(false, "email", 1, "comment");
		poll.addComment(comment);
		int newNumComments = poll.getComments().size();
		assertEquals(ogNumComments+1, newNumComments);
	}
	
}
