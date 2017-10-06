

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.cert.Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.Vector;



public class Database {
	//to connect to database
	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//constructor
	public Database (){
		//config information
		String ipaddress = "";
		String dbName = "";
		String user = "";
		String password = "";
		
		//parse the sql source file
		try{
			String configFile = "rsrc/config.txt";
			String line = null;
			
			FileReader fileReader = new FileReader(configFile);
			BufferedReader br = new BufferedReader(fileReader);
			
			//read info from config file
			while((line = br.readLine()) != null){
				String [] strSplit;
				strSplit = line.split(":");
				String id = strSplit[0];
				
				switch(id){
					case "ipaddress":
						ipaddress = strSplit[1].trim();
						break;
					case "db":
						dbName = strSplit[1].trim();
						break;
					case "user":
						user = strSplit[1].trim();
						break;
					case "password":
						password = strSplit[1].trim();
						break;
				}//switch
			}//while

			//connect to the sql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + ipaddress + "/" + dbName + "?user=" + user + 
					"&password=" + password + "&useSSL=false");
			st = conn.createStatement();
			
			br.close();
		} catch(FileNotFoundException fnfe){
			System.out.println("fnfe in Database: " + fnfe.getMessage());
		} catch (IOException ioe){
			System.out.println("ioein Database: " + ioe.getMessage());
		} catch (ClassNotFoundException cnfe){
			System.out.println("cnfe in Database: " + cnfe.getMessage());
		} catch(SQLException sqle){
			System.out.println("sqle in Database: " + sqle.getMessage());
		}
		
		//connect to the sql database
	}//constructor
	
	
	//returns the user associated with the email passed
	public User getUser(String email){
		User user = null;
		
		try{
			ps = conn.prepareStatement("SELECT password, fName, lName "
					+ "FROM Users "
					+ "WHERE email = '" + email + "';");
			rs = ps.executeQuery();
			
			rs.next();
			String password = rs.getString("password");
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			
			user = new User(email, password, fName, lName);
			
		} catch(SQLException sqle){
			System.out.println("sqle in getUser: " + sqle.getMessage());
		}
		
		return user;
	}//getUser()
	
	
	//adds a user to the database, with full name, email, and password
	public void addUser(User newUser){
		
		String fName = newUser.getFName();
		String lName = newUser.getLName();
		String email = newUser.getEmail();
		String password = newUser.getPassword();
		
		try{
			ps = conn.prepareStatement("INSERT INTO Users(fName, lName, email, password)"
					+ "VALUES('" + fName + "', '" + lName + "', '" + email + "', '" + password + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addUser: " + sqle.getMessage());
		}
	}//addUser()
	
	
	//adds and action to the database
	public void addAction(Action action){
		//get the necessary information to add an activity to the table
		String email = action.getUser();
		int userID = getUserID(email);
		Boolean isAnon = action.getIsAnon();
		String actionType = null;
		int subjectID = action.getSubjectID();
		String actionValue = null;
		
		//set action type to "CommentAction"
		//and set actionValue to the comment
		if(action instanceof CommentAction){
			actionType = "CommentAction";
			actionValue = ((CommentAction) action).getContent();
		}
		//set action type to "PollAction" 
		//and set action value to the option chosen from the poll
		else if(action instanceof PollAction){
			actionType = "PollAction";
			actionValue = ((PollAction) action).getOptionChosen();
		}
		//set action type to "RatingAction"
		//and set action value to "upVote" if rating was upvote, "downVote" otherwise
		else if(action instanceof RatingAction){
			actionType = "RatingAction";
			Boolean isUpVote = ((RatingAction) action).isUpVote();
			if(isUpVote)
				actionValue = "upVote";
			else
				actionValue = "downVote";
		}
		
		//add the activity to the Activity Table
		try{
			ps = conn.prepareStatement("INSERT INTO Activity(userID, isAnon, actionType, subjectID, actionValue)"
					+ "VALUES('" + userID + "', '" + isAnon + "', '" + actionType + "', '" 
					+ subjectID + "', '" + actionValue + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addAction: " + sqle.getMessage());
		}
		
	}//addAction()
	
	
	//returns the userID of the user with the full name passed
	private int getUserID(String email){
		int userID = 0;
		
		try{
			ps = conn.prepareStatement("SELECT userID "
					+ "FROM Users "
					+ "WHERE email = '" + email + "';");
			rs = ps.executeQuery();
			
			rs.next();
			userID = rs.getInt("userID");
		} catch(SQLException sqle){
			System.out.println("sqle in getUserID: " + sqle.getMessage());
		}
		
		return userID;
	}//getUserID()
	
	
	//add the entity passed to the database
	public void addEntity(Entity newEntity){
		
		String title = newEntity.getTitle();
		int id = newEntity.getID();
		String description = newEntity.getDescription();
		int rating = newEntity.getRating();
		String image = newEntity.getImage();
		int numViews = newEntity.getNumViews();
		Boolean isInfinite = newEntity.isInfinite();
		Calendar timeEndCal = newEntity.getTimeEnd();
		Timestamp timeEnd = calendarToTimestamp(timeEndCal);
		
		try{
			ps = conn.prepareStatement("INSERT INTO Entities(id, title, description, rating, image, numViews, isInfinite, timeEnd)"
					+ "VALUES('" + title + "', '" + description + "', '" + rating + "', '" + image + "', '" 
					+ numViews + "', '" + isInfinite + "', '" + timeEnd + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addEntity: " + sqle.getMessage());
		}
		
		//add the entity's tags to the tag table
		ArrayList<String> tags = newEntity.getTags();
		addTags(id, tags);
		
	}//addEntity()
	
	
	//add tags from an entity or poll to Tags table
	private void addTags(int id, ArrayList<String> tags){
		for(int i = 0; i < tags.size(); i++){
			String currTag = tags.get(i);
			
			try{
				ps = conn.prepareStatement("INSERT INTO Tags(subjectID, title) "
						+ "VALUES('" + id + "', '" + currTag + "')");
				ps.execute();
			} catch(SQLException sqle){
				System.out.println("sqle in addTags: " + sqle.getMessage());
			}
			
		}//for
	}//addTags()
	
	
	//translate a java Calendar object to an SQL TimeStamp object
	private Timestamp calendarToTimestamp(Calendar cal){
		long millis = cal.getTimeInMillis();
		Timestamp timestamp = new Timestamp(millis);
		return timestamp;
	}//calendarToTimeStamp()
	
	
	//translate an SQL Timestamp to a Java Calendar object
	private Calendar timestampToCalendar(Timestamp t){
		long millis = t.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar;
	}//timestampToCalendar()
	
	
	//returns an ArrayList of all entities in the database
	public ArrayList<Entity> getEntities(){
		ArrayList<Entity> entities = new ArrayList<Entity>();
		
		try{
			ps = conn.prepareStatement("SELECT title, entityID, description, rating, image, "
					+ "numViews, isInfinite, timeEnd "
					+ "FROM Entities;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				String title = rs.getString("title");		//entity title
				int entityID = rs.getInt(entityID);			//entity ID
				String description = rs.getString("descripton");	//entity description
				ArrayList<String> tags = getTags(entityID);	//entity tags
				int rating = rs.getInt(rating);				
				String image = rs.getString(image);			//image url
				int numViews = rs.getInt(numViews);
				ArrayList<CommentAction> comments = getComments(entityID);	//a list of commentActions associated with the enitity
				Boolean isInfinite = rs.getBoolean("isInfinite");
				Timestamp endTimestamp = rs.getTimestamp("timeEnd");
				Calendar timeEnd = timestampToCalendar(endTimestamp);
				
				
				//create the entity and add it to the list of entities
				Entity entity = new Entity(title, entityID, description, tags, rating, image, numViews, 
						comments, isInfinite, timeEnd);
				entities.add(entity);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getEntities: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getEntities: " + ae.getMessage());
		}
		
		return entities;
	}//getEntities()
	
	
	//returns a list of tags for the entity with ID given
	private ArrayList<String> getTags(int id){
		ArrayList<String> tags = new ArrayList<String>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		try{
			p = conn.prepareStatement("SELECT title "
					+ "FROM Entities"
					+ "WHERE subjectID = '" + id + "';");
			r = p.executeQuery();
			
			while(r.next()){
				String tag = r.getString("title");		//tag title
				tags.add(tag);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getTags: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getTags: " + ae.getMessage());
		}
		
		return tags;
	}//getTags()
	
	
	//returns a list of CommentActions associated with the entity or poll with ID passed
	private ArrayList<CommentAction> getComments(int id){
		ArrayList<CommentAction> comments = new ArrayList<CommentAction>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		try{
			p = conn.prepareStatement("SELECT a.isAnon, u.email, a.actionValue "
					+ "FROM Activities a, Users u"
					+ "WHERE subjectID = '" + id + " "
					+ "AND u.userID = a.userID "
					+ "AND a.type = 'Comment';");
			r = p.executeQuery();
			
			while(r.next()){
				Boolean isAnon = r.getBoolean("a.isAnon");
				String user = r.getString("u.email"); 		//email of the user who wrote the comment
				String content = r.getString("a.actionValue");	//the comment itself
				
				CommentAction comment = new CommentAction(isAnon, user, id, content);
				comments.add(comment);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getComments: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getComments: " + ae.getMessage());
		}
		
		return comments;
	}//getComments()
	
	
	//add the poll passed to the database
	public void addPoll(Poll newPoll){
		
		String title = newPoll.getTitle();
		int id = newPoll.getID();
		int numViews = newPoll.getNumViews();
		String image = newPoll.getImage();
		Boolean isInfinite = newPoll.isInfinite();
		Calendar timeEndCal = newPoll.getTimeEnd();
		Timestamp timeEnd = calendarToTimestamp(timeEndCal);
		
		try{
			ps = conn.prepareStatement("INSERT INTO Polls(id, title, image, numViews, isInfinite, timeEnd )"
					+ "VALUES('" + id + "', '" + title + "', '" + image + "', '" + numViews + "', '" 
					+ isInfinite + "', '" + timeEnd + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addPoll: " + sqle.getMessage());
		}
		
		//add the poll's tags to the Tag Table
		ArrayList<String> tags = newPoll.getTags();
		addTags(id, tags);
		
		//add the poll's options to the options table
		ArrayList<String> options = newPoll.getOptions();
		addOptions(id, options);
		
	}//addPoll()
	
	
	//add options from a poll to Options table
	private void addOptions(int pollID, ArrayList<String> options){
		for(int i = 0; i < options.size(); i++){
			String currOption = options.get(i);		
			
			//when first adding options for a poll to the database, numVotes for each option
			//should be 0. Update the numVotes later when a PollAction occurs
			try{
				ps = conn.prepareStatement("INSERT INTO Options(pollID, title, numVotes) "
						+ "VALUES('" + pollID + "', '" + currOption + "', '0')");
				ps.execute();
			} catch(SQLException sqle){
				System.out.println("sqle in addOptions: " + sqle.getMessage());
			}
			
		}//for
	}//addOptions()
	
	
	//return a list of all the polls in the database
	public ArrayList<Poll> getPolls(){
		ArrayList<Poll> polls = new ArrayList<Poll>();
		
		try{
			ps = conn.prepareStatement("SELECT title, pollID, image, numViews, isInfinite, timeEnd "
					+ "FROM Polls;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				String title = rs.getString("title");		//poll title
				int pollID = rs.getInt("pollID");			//poll ID
				String image = rs.getString(image);			//image url
				ArrayList<String> tags = getTags(pollID);	//poll tags
				ArrayList<String> options = getOptions(pollID);	//poll options	
				HashMap<String, Integer> numVotes = getPollVotes(pollID);
				int numViews = rs.getInt(numViews);
				ArrayList<CommentAction> comments = getComments(pollID);	//a list of commentActions associated with the enitity
				Boolean isInfinite = rs.getBoolean("isInfinite");
				Timestamp endTimestamp = rs.getTimestamp("timeEnd");
				Calendar timeEnd = timestampToCalendar(endTimestamp);
				
				
				//create the poll and add it to the list of polls
				Poll poll = new Poll(title, pollID, image, tags, options, numVotes, numViews, 
						comments, isInfinite, timeEnd);
				polls.add(poll);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getEntities: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getEntities: " + ae.getMessage());
		}
		
		return polls;
	}//getpolls()
	
	
	//returns a list of options for the poll with ID given
	private ArrayList<String> getOptions(int id){
		ArrayList<String> options = new ArrayList<String>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		try{
			p = conn.prepareStatement("SELECT title "
					+ "FROM Options"
					+ "WHERE pollID = '" + id + "';");
			r = p.executeQuery();
			
			while(r.next()){
				String option = r.getString("title");		//option title
				options.add(option);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getOptions: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getOptions: " + ae.getMessage());
		}
		
		return options;
	}//getOptions()
	
	
	//returns a map of poll options to number of votes for the poll with ID passed
	private HashMap<String, Integer> getPollVotes(int id){
		HashMap<String, Integer> numVotes = new HashMap<String, Integer>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		try{
			p = conn.prepareStatement("SELECT title, numVotes "
					+ "FROM Options"
					+ "WHERE pollID = '" + id + "';");
			r = p.executeQuery();
			
			while(r.next()){
				String option = r.getString("title");		//option title
				int num = r.getInt("numVotes");				//current number of votes for option in poll
				numVotes.put(option, num);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getPollVotes: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getPollVotes: " + ae.getMessage());
		}
		
		return numVotes;
	}//getPollVotes()
	
	
	//adds 1 view to the entity or poll with id passed
	public void addNumView(int subjectID, String subjectType){
		String tableName = "";
		String idType = "";
		int numViews = 0;
		
		if(subjectType.equals("Entity")){
			tableName = "Entities";
			idType = "entityID";
		}
		else{
			tableName = "Polls";
			idType = "pollID";
		}
		
		// get the numViews for the entity or poll
		try {
			ps = conn.prepareStatement("SELECT numViews "
					+ "FROM " + tableName + " "
					+ "WHERE " + idType + " = '" + subjectID + "';");

			rs.next();
			numViews = rs.getInt("numViews");
		} catch (SQLException sqle) {
			System.out.println("sqle in addNumView: " + sqle.getMessage());
		}

		numViews++;

		// update the numViews
		try {
			ps = conn.prepareStatement("UPDATE " + tableName + " " 
					+ "SET numViews = " + numViews + " "
					+ "WHERE " + idType + " = '" + subjectID + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in addNumView: " + sqle.getMessage());
		}
		
	}//addNumView()
	
	
}
