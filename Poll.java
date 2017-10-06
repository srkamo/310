import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class Poll {

	
	private String title; 
	private int id; 
	private ArrayList<String> tags;
	private ArrayList<String> options; 
	private int numViews;
	private int numVotes; 
	private ArrayList<CommentAction> comments; 
	private Boolean isInfinite; 
	private Calendar timeEnd; 
	private String image; //this is new 
	private HashMap<String, Integer> pollVotes;
	
	
	public Poll(String title1, int id1, ArrayList<String> tags1, ArrayList<String> options1, Boolean isInfinite1, Calendar timeEnd1, String image1) {
		title = title1; 
		id = id1; 
		for (int i = 0; i< tags.size(); i++) {
			tags.add(tags.get(i));
		}		
		options = options1; 
		isInfinite = isInfinite1; 
		timeEnd = (Calendar) timeEnd1.clone();
		image = image1; 
		numVotes = 0; 
		numViews = 0;
	}
	
	public Poll(Poll poll) { // Copy Constructor
		title = poll.getTitle(); 
		id = poll.getID(); 
		for (int i = 0; i< poll.getTags().size(); i++) {
			tags.add(poll.getTags().get(i));
		}
		options = poll.getOptions();
		numViews = poll.getNumViews(); 
		numVotes = poll.getNumVotes(); 
		for (int i = 0; i< poll.getComments().size(); i++) {
			comments.add(poll.getComments().get(i));
		}		
		isInfinite = poll.isInfinite();
		timeEnd = (Calendar) poll.getTimeEnd().clone();
		image = poll.getImage();
		pollVotes = poll.getPollResults();
	}
	
	public String getTitle() {
		return title; 
	}
	
	public int getID() {
		return id;
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}
	private void addTag(String newTag) {
		// if tag doesn't exist 
		tags.add(newTag);
	}
	private ArrayList<String> getOptions() {
		return options; 
	}
	
	public int getNumViews() {
		return numViews; 
	}
	public ArrayList<CommentAction> getComments() {
		return comments; 
	}
	public Boolean pollClosed() {
		Calendar rightNow = Calendar.getInstance();
		int isClosed = timeEnd.compareTo(rightNow);
		if (isInfinite()) { 
			return false;
		}
		else if (isClosed <= 0) {
			return true; 
		}
		else {
			return false; 
		}
	}
	public HashMap<String,Integer> getPollResults() {
		return pollVotes; 
	}
	public void vote(String option) {
		if (!pollClosed()) {
			int currVotes = pollVotes.get(option);
			int newVotes = currVotes + 1;
			pollVotes.put(option, newVotes); 
		}
		
	}
	public String getImage() { //this is new 
		return image; 
	}

	public void addView() { // this is new 
		numViews++;
	}
	public Boolean isInfinite() {//this is new 
		return isInfinite;
	}
	public Calendar getTimeEnd() { //this is new 
		return timeEnd;
	}
	public void addComment(CommentAction comment) { // this is new 
		comments.add(comment);
	}
	public int getNumVotes() { // this is new 
		return numVotes; 
	}
}
