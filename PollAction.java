
public class PollAction extends Action {

	private String optionChosen;
	
	//constructor
	public PollAction(Boolean isAnon, String userEmail, int subjectID, String optionChosen){
		super(isAnon, userEmail, subjectID);
		this.optionChosen = optionChosen;
	}//constructor
	
	//get option string
	public String getOptionChosen(){
		return optionChosen;
	}//getOptionChosen
	
}
