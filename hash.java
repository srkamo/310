package knowitall;


	     import java.security.*;
	     import java.math.*;
	     
	     public class hash {

	        public String hashPassword(String password) throws Exception{
	        	
	          MessageDigest m=MessageDigest.getInstance("MD5");
	           m.update(password.getBytes(),0,password.length());
	          
	          String hashedPassword = new BigInteger(1,m.digest()).toString(16);
	          
	          return hashedPassword;
	        }
	        
	        
	        public boolean checkPassword(String attempt, String password) throws Exception{
	        	
	        	String check=hashPassword(attempt);
	        	
	        	if (check.equals(password))
	        	return true;
	        	
	        	else	
	        	return false;
	        	
	    	}
	        
	    }


