package Business;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {
	@Test
	public void testGetFName() {
		User u = new User("monger@usc.edu", "12345", "Natalie", "Monger"); 
		String fName = u.getFName(); 
		assertEquals("Natalie", fName);
	}
	
	@Test
	public void testGetLName() {
		User u = new User("monger@usc.edu", "12345", "Natalie", "Monger"); 
		String lName = u.getLName(); 
		assertEquals("Monger", lName);
	}
	
	@Test
	public void testGetEmail() {
		User u = new User("monger@usc.edu", "12345", "Natalie", "Monger"); 
		String email = u.getEmail(); 
		assertEquals("monger@usc.edu", email);
	}
	@Test
	public void testGetPassword() {
		User u = new User("monger@usc.edu", "12345", "Natalie", "Monger"); 
		String password = u.getPassword(); 
		assertEquals("12345", password);
	}
	
	public static void main(String [] args){
		UserTest ut = new UserTest(); 
		ut.testGetFName(); 
		ut.testGetLName();
		ut.testGetEmail();
		ut.testGetPassword(); 
	}
}
