import java.util.Calendar;
import java.util.List;
import java.util.HashMap;

public class Poll {

	
	private String title; 
	private int id; 
	private List<String> tags;
	private List<String> options; 
	private int numViews;
	private int numVotes; 
	private List<commentActions> comments; 
	private Boolean isInfinite; 
	private Calendar timeEnd; 
	private String image; //this is new 
	private HashMap<String, Integer> pollVotes;
	
	
	public Poll(String title1, int id1, List<String> tags1, List<String> options1, Boolean isInfinite1, Calendar timeEnd1, String image1) {
		title = title1; 
		id = id1; 
		tags = tags1; // does this work? -> can Lists be copied like this?
		options = options1; // does this work? -> can Lists be copied like this?
		isInfinite = isInfinite1; 
		timeEnd = timeEnd1; // does this work? -> can Calendars be copied like this?
		image = image1; 
		numVotes = 0; 
		numViews = 0;
	}
	
	public Poll(Poll poll) { // Copy Constructor
		title = poll.getTitle(); 
		id = poll.getID(); 
		tags = poll.getTags(); // does this work? 
		options = poll.getOptions();
		numViews = poll.getNumViews(); 
		numVotes = poll.getNumVotes(); 
		comments = poll.getComments(); //does this work?
		isInfinite = poll.isInfinite();
		timeEnd = poll.getTimeEnd(); // does this work?
		image = poll.getImage();
		pollVotes = poll.getPollResults();
	}
	
	public String getTitle() {
		return title; 
	}
	
	public int getID() {
		return id;
	}
	
	public List<String> getTags() {
		return tags;
	}
	private void addTag(String newTag) {
		// if tag doesn't exist 
		tags.add(newTag);
	}
	private List<String> getOptions() {
		return options; 
	}
	
	public int getNumViews() {
		return numViews; 
	}
	public List<commentActions> getComments() {
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
