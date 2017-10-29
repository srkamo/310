package Business;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailVerifierTest {

	@SuppressWarnings("null")
	@Test
	public void testValidate() {
		String email = "bmuller@usc.edu";
		
		EmailVerifier testEmail = new EmailVerifier();
		
		Boolean expectedResult=true;
		
		Boolean actualResult = testEmail.validate(email);
		
		 assertEquals(expectedResult, actualResult);
	}

}
