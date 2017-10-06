package knowitall;


	     import java.security.*;
	     import java.math.*;
	     
	     public class hash {

	        public String hashPassword(String password) throws Exception{
	        	
	          MessageDigest m=MessageDigest.getInstance("MD5");
	           m.update(password.getBytes(),0,password.length());
	          
	          String hashedPassword = new BigInteger(1,m.digest()).toString(16);
	          
	          System.out.println("Password: "+password); 
	          
	          System.out.println("Hashed password: "+hashedPassword); 
	          
	          return hashedPassword;
	        }
	        
	        
	        public boolean checkPassword(String attempt, String password) throws Exception{
	        	
	        	String check=hashPassword(attempt);
	        	
	        	System.out.println(check);
	        	System.out.println(password);
	        	
	        	if (check.equals(password))
	        	{
	        		System.out.println("true");
	        		return true;
	        	}
	        	
	        	else	
	        	System.out.println("false");
	        	return false;
	        	
	    	}
	        
	    }


