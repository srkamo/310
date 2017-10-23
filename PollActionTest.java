package Business;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Business.PollAction;

public class PollActionTest {
	@Test
	public void testGetOption() {
		PollAction pa = new PollAction(true, "monger@usc.edu", 6, "SparkSC");
		String optionChosen = pa.getOptionChosen(); 
	    assertEquals("SparkSC", optionChosen);
	}
	
	public static void main(String [] args){
		PollActionTest pat = new PollActionTest(); 
		pat.testGetOption(); 
	}
}
