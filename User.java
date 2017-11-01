package Business;

import java.util.ArrayList;

public class User {
	
	private String email;
	private String password;
	private String fName;
	private String lName;
	private ArrayList<String> followers;
	private ArrayList<String> following;
	
	//constructor
	public User (String email, String password, String fName, String lName, 
			ArrayList<String> followers, ArrayList<String> following){
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.followers = followers;
		this.following = following;
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
	public ArrayList<String> getFollowers(){
		return followers;
	}
	public ArrayList<String> getFollowing(){
		return following;
	}

}