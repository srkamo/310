

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
		String ipaddress = "127.0.0.1";
		String dbName = "knowItAll";
		String user = "root";
		String password = "root";
		
		//parse the sql source file
		try{
//			String configFile = "config.txt";
//			///KnowItAll/src/config.txt
//			String line = null;
//			
//			FileReader fileReader = new FileReader(configFile);
//			BufferedReader br = new BufferedReader(fileReader);
//			
//			//read info from config file
//			while((line = br.readLine()) != null){
//				String [] strSplit;
//				strSplit = line.split(":");
//				String id = strSplit[0];
//				
//				switch(id){
//					case "ipaddress":
//						ipaddress = strSplit[1].trim();
//						break;
//					case "db":
//						dbName = strSplit[1].trim();
//						break;
//					case "user":
//						user = strSplit[1].trim();
//						break;
//					case "password":
//						password = strSplit[1].trim();
//						break;
//				}//switch
//			}//while

			//connect to the sql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + ipaddress + "/" + dbName + "?user=" + user + 
					"&password=" + password + "&useSSL=false");
			st = conn.createStatement();
			
//			br.close();
//		} catch(FileNotFoundException fnfe){
//			System.out.println("fnfe in Database: " + fnfe.getMessage());
//		} catch (IOException ioe){
//			System.out.println("ioein Database: " + ioe.getMessage());
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
		Boolean infinite = newEntity.isInfinite();
		int isInfinite = 0;
		if(infinite){ isInfinite = 1; }
		
		Calendar timeEndCal = newEntity.getTimeEnd();
		Timestamp timeEnd = calendarToTimestamp(timeEndCal);
		
		try{
			ps = conn.prepareStatement("INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)"
					+ "VALUES('" + id + "', '" + title + "', '" + description + "', '" + rating + "', '" + image + "', '" 
					+ numViews + "', '" + isInfinite + "', '" + timeEnd + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addEntity: " + sqle.getMessage());
		}
		
		//add the entity's tags to the tag table
		ArrayList<String> tags = newEntity.getTags();
		addTags(id, tags, "Entity");
		
	}//addEntity()
	
	
	//add tags from an entity or poll to Tags table
	private void addTags(int id, ArrayList<String> tags, String type){
		String tagTable = "";
		String idType = "";
		
		if(type.equals("Entity")){
			tagTable = "EntityTags";
			idType = "entityID";
		}
		else{
			tagTable = "PollTags";
			idType = "pollID";
		}
		
		for(int i = 0; i < tags.size(); i++){
			String currTag = tags.get(i);
			
			try{
				ps = conn.prepareStatement("INSERT INTO " + tagTable + "(" + idType + ", title) "
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
				int entityID = rs.getInt("entityID");			//entity ID
				String description = rs.getString("description");	//entity description
				ArrayList<String> tags = getTags(entityID);	//entity tags
				int rating = rs.getInt("rating");				
				String image = rs.getString("image");			//image url
				int numViews = rs.getInt("numViews");
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
					+ "FROM EntityTags "
					+ "WHERE entityID = '" + id + "';");
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
					+ "FROM Activities a, Users u "
					+ "WHERE subjectID = '" + id + "' "
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
		Boolean infinite = newPoll.isInfinite();
		int isInfinite = 0;
		if(infinite){ isInfinite = 1; }
		
		Calendar timeEndCal = newPoll.getTimeEnd();
		Timestamp timeEnd = calendarToTimestamp(timeEndCal);
		
		try{
			ps = conn.prepareStatement("INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd )"
					+ "VALUES('" + id + "', '" + title + "', '" + image + "', '" + numViews + "', '" 
					+ isInfinite + "', '" + timeEnd + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addPoll: " + sqle.getMessage());
		}
		
		//add the poll's tags to the Tag Table
		ArrayList<String> tags = newPoll.getTags();
		addTags(id, tags, "Poll");
		
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
				String image = rs.getString("image");			//image url
				ArrayList<String> tags = getTags(pollID);	//poll tags
				ArrayList<String> options = getOptions(pollID);	//poll options	
				HashMap<String, Integer> numVotes = getPollVotes(pollID);
				int numViews = rs.getInt("numViews");
				ArrayList<CommentAction> comments = getComments(pollID);	//a list of commentActions associated with the enitity
				Boolean isInfinite = rs.getBoolean("isInfinite");
				Timestamp endTimestamp = rs.getTimestamp("timeEnd");
				Calendar timeEnd = timestampToCalendar(endTimestamp);
				
				
				//create the poll and add it to the list of polls
				Poll poll = new Poll(title, pollID, tags, options, numViews, comments, isInfinite, 
						timeEnd, image, numVotes);
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
					+ "FROM Options "
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
					+ "FROM Options "
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
			rs = ps.executeQuery();
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
	
	
	//returns a list of Polls which relate to the keyword passed
		public ArrayList<Poll> searchForPolls(String keyword){
			keyword = keyword.toLowerCase();
			//start with all the polls in the database
			ArrayList<Poll> allPolls = getPolls();
			ArrayList<Poll> returnPolls = new ArrayList<Poll>();
			
			//go through the list of polls and add the ones that pertain to the keyword
			for(int i = 0; i < allPolls.size(); i++){
				Poll currPoll = allPolls.get(i);
				String title = currPoll.getTitle();
				ArrayList<String> tags = currPoll.getTags();
				Boolean match = false;
				
				//check if title contains keywords
				if((title.toLowerCase()).contains(keyword)){
					match = true;
				}
				//if title does not contain keyword, check tags
				if(!match){
					for(int j = 0; j < tags.size(); j++){
						if((tags.get(j)).toLowerCase().contains(keyword)){
							match = true;
							break;
						}//if
					}//for j
				}//if
				
				//if not match, delete from polls list
				if(match){
					returnPolls.add(currPoll);
				}
				
			}//for i
			
			return returnPolls;
		}//searchForPolls()
		
		
		//returns a list of Entities which relate to the keyword passed
		public ArrayList<Entity> searchForEntities(String keyword){
			keyword = keyword.toLowerCase();
			//start with all the entities in the database
			ArrayList<Entity> allEntities = getEntities();
			System.out.println("total num entities: " + allEntities.size());
			ArrayList<Entity> returnEntities = new ArrayList<Entity>();
			
			//go through the list of polls and delete the ones that do not pertain to the keyword
			for(int i = 0; i < allEntities.size(); i++){
				Entity currEntity = allEntities.get(i);
				String title = currEntity.getTitle();
				String description = currEntity.getDescription();
				ArrayList<String> tags = currEntity.getTags();
				Boolean match = false;
				
				//check if title or description contains keywords
				if((title.toLowerCase()).contains(keyword) || (description.toLowerCase()).contains(keyword)){
					match = true;
				}
				//if title or description does not contain keyword, check tags
				if(!match){
					for(int j = 0; j < tags.size(); j++){
						String currTag = tags.get(j);
						if(currTag.equalsIgnoreCase("fucking idiot")){
							int a = 0;
							a++;
						}
						if(currTag.toLowerCase().equalsIgnoreCase(keyword)){
							match = true;
							break;
						}//if
					}//for j
				}//if
				
				//if not match, delete from polls list
				if(match){
					returnEntities.add(currEntity);
				}
				
			}//for i
			
			return returnEntities;
		}//searchForEntities()
	
	
	public static void main(String [] args){
		Database db = new Database();
		
		//test addUser
//		User newUser = new User("mbent@usc.edu", "password", "Morgan", "Bent");
//		db.addUser(newUser);
		
		//test getUser
//		User user = db.getUser("mbent@usc.edu");
//		System.out.println("Email: " + user.getEmail());
//		System.out.println("fName: " + user.getFName());
//		System.out.println("lName: " + user.getLName());
		
		//test addEntity
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("yum");
		Calendar calendar = Calendar.getInstance();
		Entity newEntity = new Entity("Pizza", 1, "pizza pizza", tags, true, calendar, "anImageURL");
		db.addEntity(newEntity);
		db.addNumView(1, "Entity");
		db.addNumView(1, "Entity");
		db.addNumView(1, "Entity");
		db.addNumView(1, "Entity");
		
		
		ArrayList<String> tags1 = new ArrayList<String>();
		tags.add("yum");
		Calendar cal = Calendar.getInstance();
		Entity newEntity1 = new Entity("hello", 2, "hi", tags1, true, calendar, "url");
		db.addEntity(newEntity1);
		db.addNumView(2, "Entity");
		db.addNumView(2, "Entity");
		
		
		Entity newEntity2 = new Entity("Morgan", 3, "hi", tags1, true, calendar, "url");
		db.addEntity(newEntity2);
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		db.addNumView(3, "Entity");
		
		
		Entity newEntity3 = new Entity("Sebastian", 4, "hi", tags1, true, calendar, "url");
		db.addEntity(newEntity3);
		db.addNumView(4, "Entity");
		db.addNumView(4, "Entity");
		db.addNumView(4, "Entity");
		db.addNumView(4, "Entity");
		db.addNumView(4, "Entity");
		db.addNumView(4, "Entity");
		
		
		
		Entity newEntity4 = new Entity("Natalie", 5, "hi", tags1, true, calendar, "url");
		db.addEntity(newEntity4);
		db.addNumView(5, "Entity");
		db.addNumView(5, "Entity");
		db.addNumView(5, "Entity");

		
		//test addPoll
//		ArrayList<String> tags = new ArrayList<String>();
//		tags.add("tag1");
//		ArrayList<String> options = new ArrayList<String>();
//		options.add("option1");
//		options.add("option2");
//		Calendar calendar = Calendar.getInstance();
//		Poll newPoll = new Poll("my poll", 1, tags, options, true, calendar, "someURL");
//		db.addPoll(newPoll);
		
		//ArrayList<Poll> allPolls = db.getPolls();
		ArrayList<Poll> search = db.searchForPolls("my");
		System.out.println(search.size());

	}//main
	
}
