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
	
	public Poll(String title1, int id1, List<String> tags1, List<String> options1, int numViews1, int numVotes1, List<commentActions> comments1, Boolean isInfinite1, Calendar timeEnd1, String image1, HashMap<String, Integer> pollVotes1) { // Copy Constructor
		title = title1; 
		id = id1; 
		for (int i = 0; i< tags1.size(); i++) {
			tags.add(tags1.get(i));
		}
		options = options1;
		numViews = numViews1; 
		numVotes = numVotes1; 
		for (int i = 0; i< comments1.size(); i++) {
			comments.add(comments1.get(i));
		}		
		isInfinite = isInfinite1;
		timeEnd = (Calendar) timeEnd1.clone();
		image = image1;
		pollVotes = new HashMap<String, Integer>(pollVotes1);
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
