
public class CommentAction extends Action {

	private String content;
	
	//constructor
	public CommentAction(Boolean isAnon, String userEmail, int subjectID, String comment){
		super(isAnon, userEmail, subjectID);
		this.content = comment;
	}//constructor
	
	//get comment string
	public String getContent(){
		return content;
	}//getContent
	
}
