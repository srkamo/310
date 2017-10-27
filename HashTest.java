package Business;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import Business.Hash;
import Business.User;
import Database.Database;


public class HashTest {
	@Test 
	public void testHash() {
		Database db = new Database();
		User newUser = db.getUser("nmonger@usc.edu");
		 
		
		Hash hash = new Hash();
		String newHash = hash.hash("password2");
		assertEquals(newUser.getPassword(), newHash); 
	}
}
