package Business;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import Business.CommentAction;


public class CommentActionTest {
	@Test 
	public void testGetContent() {
		CommentAction commentAct = new CommentAction(true,"jasonwit@usc.edu", 7, "THIS IS A TEST COMMENT");
		String comment = commentAct.getContent();
		assertEquals("THIS IS A TEST COMMENT", comment);
	}
	public static void main(String args[]) { 
		CommentActionTest cat = new CommentActionTest();
		cat.testGetContent();
	}
}


