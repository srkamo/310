
public class Action {
	
	private Boolean isAnon;
	private String userEmail;
	private int subjectID;
	
	//constructor
	public Action(Boolean isAnon, String userEmail, int subjectID){
		this.isAnon = isAnon;
		this.userEmail = userEmail;
		this.subjectID = subjectID;
	}//constructor
	
	//return the email of the user who performed the action
	public String getUser(){
		return userEmail;
	}//getUser()
	
	//return isAnon
	public Boolean getIsAnon(){
		return isAnon;
	}//getIsAnon()
	
	//return subjectID
	public int getSubjectID(){
		return subjectID;
	}//getSubjectID()

}
