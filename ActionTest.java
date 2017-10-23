package Business;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import Business.Action;

public class ActionTest {
	@Test
	public void testGetUserEmail() {
		Action action = new Action(true, "monger@usc.edu", 7);
	    String userEmail = action.getUser(); 
	    assertEquals("monger@usc.edu", userEmail);
	}
	
	@Test
	public void testGetIsAnon() {
		Action action = new Action(true, "monger@usc.edu", 7);
	    Boolean isAnon = action.getIsAnon(); 
	    assertEquals(true, isAnon);
	}
	
	@Test
	public void testSubjectID() {
		Action action = new Action(true, "monger@usc.edu", 7);
	    int subjectID = action.getSubjectID(); 
	    assertEquals(7, subjectID);
	}
	
	public static void main(String [] args){
		ActionTest at = new ActionTest(); 
		at.testGetUserEmail(); 
		at.testGetIsAnon();
		at.testSubjectID(); 
	}
}
