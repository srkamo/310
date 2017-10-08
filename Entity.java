import java.util.Calendar;
import java.util.ArrayList;

public class Entity {
	
	private String title; 
	private int id; 
	private String description; 
	private ArrayList<String> tags;
	private int rating; 
	private String image; 
	private int numViews; 
	private ArrayList<CommentAction> comments; 
	private Boolean isInfinite; 
	private Calendar timeEnd; 
	
	// Entity Constructor
	public Entity(String title1, int id1, String description1, ArrayList<String> tags1, Boolean isInfinite1, Calendar timeEnd1, String image1) {
		title = title1; 
		id = id1; 
		description = description1;
		tags = new ArrayList<String>(tags1); // does this work? -> can ArrayLists be copied like this?
		isInfinite = isInfinite1; 
		timeEnd = timeEnd1; // does this work? -> can Calendars be copied like this?
		image = image1; 
		numViews = 0; 
	}
	
	
	public Entity(String title1, int id1, String description1, ArrayList<String> tags1, int rating1, String image1, int numViews1, ArrayList<CommentAction> comments1, Boolean isInfinite1, Calendar timeEnd1) { 
		title = title1;
		id = id1;
		description = description1;
		tags = tags1;
		comments = comments1;	
		rating = rating1;
		image = image1;
		numViews = numViews1;	
		isInfinite = isInfinite1;
		timeEnd = timeEnd1;
	}
	
	// Get Entity Title 
	public String getTitle() {
		return title; 
	}
	
	//Get Entity ID 
	public int getID() {
		return id;
	}
	
	//Get  Entity Description 
	public String getDescription() {
	
		return description; 
	}
	
	// Get Entity (Search) Tags 
	public ArrayList<String> getTags() {
		return tags;
	}
	
	// Add new Tag to Entity 
	public void addTag(String newTag) {
		tags.add(newTag); 
	}
	
	//New UpVote for Entity
	public void upVote() {
		rating++;
	}
	
	//New DownVote for Entity
	public void downVote() {
		rating--;
	}
	
	//Get Entity Rating 
	public int getRating() { 
		return rating; 
	}
	
	//Get Entity Image 
	public String getImage() {
		return image; 
	}
	
	//Get Entity numViews
	public int getNumViews() {
		return numViews; 
	}
	
	//New View for Entity
	public void addView() {
		numViews++;
	}
	
	//Get Comments for Entity
	public ArrayList<CommentAction> getComments() {
		return comments;
	}
	
	//Entity is Closed
	public Boolean ratingClosed() {
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
	public Boolean isInfinite() { // this is new 
		return isInfinite;
	}	
	public Calendar getTimeEnd() { // this is new 
		return timeEnd;
	}
	public void addComment(CommentAction comment) { // this is new 
		comments.add(comment);
	}

}