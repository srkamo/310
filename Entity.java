import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Entity {
	
	private String title; 
	private int id; 
	private String description; 
	private List<String> tags;
	private int rating; 
	private String image; 
	private int numViews; 
	private List<commentActions> comments; 
	private Boolean isInfinite; 
	private Calendar timeEnd; 
	private int numVotes; // this is new 
	
	// Entity Constructor
	public Entity(String title1, int id1, String description1, List<String> tags1, Boolean isInfinite1, Calendar timeEnd1, String image1) {
		title = title1; 
		id = id1; 
		description = description1;
		tags = new ArrayList<String>(tags1); // does this work? -> can Lists be copied like this?
		isInfinite = isInfinite1; 
		timeEnd = timeEnd1; // does this work? -> can Calendars be copied like this?
		image = image1; 
		numVotes = 0; 
		numViews = 0; 
	}
	
	public Entity(Entity entity) { // copy constructor 
		title = entity.getTitle();
		id = entity.getID();
		description = entity.getDescription();
		tags = entity.getTags(); // does this work? 
		rating = entity.getRating();
		image = entity.getImage();
		numViews = entity.getNumViews();
		comments = entity.getComments(); //does this work? 
		isInfinite = entity.isInfinite();
		timeEnd = entity.getTimeEnd(); // does this work? 
		numVotes = entity.getNumVotes();
		
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
	public List<String> getTags() {
		return tags;
	}
	
	// Add new Tag to Entity 
	public void addTag(String newTag) {
		tags.add(newTag); 
	}
	
	//New UpVote for Entity
	public void upVote() {
		rating++;
		numVotes++;
	}
	
	//New DownVote for Entity
	public void downVote() {
		rating--;
		numVotes++;
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
	public List<commentActions> getComments() {
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
	public int getNumVotes() { // this is new
		return numVotes; 
	}
}
