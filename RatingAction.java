
public class RatingAction extends Action {

	private Boolean isUpVote;
	
	//constructor
	public RatingAction(Boolean isAnon, String userEmail, int subjectID, Boolean isUpVote){
		super(isAnon, userEmail, subjectID);
		this.isUpVote = isUpVote;
	}//constructor
	
	//return true if rating was upvate, false otherwise
	public Boolean isUpVote(){
		return isUpVote;
	}//isUpVote
	
}
