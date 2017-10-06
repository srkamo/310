
public class User {
	
	private String email;
	private String password;
	private String fName;
	private String lName;
	
	//constructor
	public User (String email, String password, String fName, String lName){
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}//constructor
	
	public String getFName(){
		return fName;
	}
	public String getLName(){
		return lName;
	}
	public String getEmail(){
		return email;
	}
	public String getPassword(){
		return password;
	}

}
