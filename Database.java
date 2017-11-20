package Database; 

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

import Business.*;




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
			//connect to the sql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + ipaddress + "/" + dbName + "?user=" + user + 
					"&password=" + password + "&useSSL=false");
			st = conn.createStatement();

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
			
			ArrayList<String> followers = getFollowers(email);
			ArrayList<String> following = getFollowing(email);
			
			user = new User(email, password, fName, lName, followers, following);
			
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
			newVote(actionValue);
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
			ps = conn.prepareStatement("INSERT INTO Activities(userID, isAnon, type, subjectID, actionValue)"
					+ "VALUES('" + userID + "', '" + ((isAnon)? 1:0) + "', '" + actionType + "', '" 
					+ subjectID + "', '" + actionValue + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addAction: " + sqle.getMessage());
		}
		
	}//addAction()
	
	
	//adds 1 to the count of votes in the Options table
	private void newVote(String option){
		int numVotes = 0;
		
		// get the numVotes for the option
		try {
			ps = conn.prepareStatement("SELECT numVotes "
					+ "FROM Options "
					+ "WHERE title = '" + option + "';");

			rs = ps.executeQuery();
			rs.next();
			numVotes = rs.getInt("numVotes");
		} catch (SQLException sqle) {
			System.out.println("sqle in addVote: " + sqle.getMessage());
		}

		numVotes++;

		// update the numViews
		try {
			ps = conn.prepareStatement("UPDATE Options " 
					+ "SET numVotes = " + numVotes + " "
					+ "WHERE title = '" + option + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in addVote: " + sqle.getMessage());
		}
	}//newVote()
	
	
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
	
	
	//returns a list of actions done by the user with email passed
	public ArrayList<Action> getUserActions(String email){
		ArrayList<Action> userActions = new ArrayList<Action>();
		int userID = this.getUserID(email);
		
		try{
			ps = conn.prepareStatement("SELECT isAnon, type, subjectID, actionValue "
					+ "FROM Activities "
					+ "WHERE userID = " + userID + ";");
			rs = ps.executeQuery();
			
			while(rs.next()){
				Boolean isAnon = rs.getBoolean("isAnon");
				String type = rs.getString("type");
				int subjectID = rs.getInt("subjectID");
				String actionValue = rs.getString("actionValue");
				
				//create the action and add it to the list of actions
				Action newAction;
				
				//comment action
				if(type.equals("CommentAction")){
					newAction = new CommentAction(isAnon, actionValue, subjectID, actionValue);
				}
				//pol action
				else if(type.equals("PollAction")){
					newAction = new PollAction(isAnon, email, subjectID, actionValue);
				}
				// RatingAction
				else{
					Boolean isUpVote = true;
					
					if(actionValue.equals("downVote")){
						isUpVote = false;
					}
					newAction = new RatingAction(isAnon, email, subjectID, isUpVote);
				}//else
				
				//add newAction to the list of actions
				userActions.add(newAction);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getEntities: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getEntities: " + ae.getMessage());
		}
		
		return userActions;
	}//getUserActions()
	
	
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
		
		String creatorEmail = newEntity.getCreator();
		int creatorID = getUserID(creatorEmail);
		
		Boolean anonCreator = newEntity.creatorIsAnon();
		int creatorAnon = 0;
		if(anonCreator){ creatorAnon = 1; }
		
		try{
			ps = conn.prepareStatement("INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)"
					+ "VALUES('" + id + "', '" + title + "', '" + description + "', '" + rating + "', '" + image + "', '" 
					+ numViews + "', '" + isInfinite + "', '" + timeEnd + "', '" + creatorID + "', '" + creatorAnon + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println(" title: " + title);
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
			ps = conn.prepareStatement("SELECT e.title, e.entityID, e.description, e.rating, e.image, "
					+ "e.numViews, e.isInfinite, e.timeEnd, e.anonCreator, u.email "
					+ "FROM Entities e, Users u "
					+ "WHERE e.creatorID = u.userID;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				String title = rs.getString("e.title");		//entity title
				int entityID = rs.getInt("e.entityID");			//entity ID
				String description = rs.getString("e.description");	//entity description
				ArrayList<String> tags = getEntityTags(entityID);	//entity tags
				int rating = rs.getInt("e.rating");				
				String image = rs.getString("e.image");			//image url
				int numViews = rs.getInt("e.numViews");
				ArrayList<CommentAction> comments = getComments(entityID);	//a list of commentActions associated with the enitity
				Boolean isInfinite = rs.getBoolean("e.isInfinite");
				Timestamp endTimestamp = rs.getTimestamp("e.timeEnd");
				Calendar timeEnd = timestampToCalendar(endTimestamp);
				String creatorEmail = rs.getString("u.email");
				Boolean anonCreator = rs.getBoolean("e.anonCreator");
				
				//create the entity and add it to the list of entities
				Entity entity = new Entity(title, entityID, description, tags, rating, image, numViews, 
						comments, isInfinite, timeEnd, creatorEmail, anonCreator);
				entities.add(entity);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getEntityTags: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getEntityTags: " + ae.getMessage());
		}
		
		return entities;
	}//getEntities()
	
	
	//returns a list of tags for the entity with ID given
	private ArrayList<String> getEntityTags(int id){
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
	
	
	//returns a list of tags for the entity with ID given
	private ArrayList<String> getPollTags(int id){
		System.out.println("getting poll tags");
		ArrayList<String> tags = new ArrayList<String>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		try{
			p = conn.prepareStatement("SELECT title "
					+ "FROM PollTags "
					+ "WHERE pollID = '" + id + "';");
			r = p.executeQuery();
			
			while(r.next()){
				String tag = r.getString("title");		//tag title
				tags.add(tag);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getPollTags: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getPollTags: " + ae.getMessage());
		}
		
		System.out.println("num poll tags: " + tags.size());
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
					+ "AND a.type = 'CommentAction';");
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
		
		String creatorEmail = newPoll.getCreator();
		int creatorID = getUserID(creatorEmail);
		
		Boolean anonCreator = newPoll.creatorIsAnon();
		int creatorAnon = 0;
		if(anonCreator){ creatorAnon = 1; }
		
		try{
			ps = conn.prepareStatement("INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)"
					+ "VALUES('" + id + "', '" + title + "', '" + image + "', '" + numViews + "', '" 
					+ isInfinite + "', '" + timeEnd + "', " + creatorID + ", " + creatorAnon + ")");
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
			ps = conn.prepareStatement("SELECT p.title, p.pollID, p.image, p.numViews, p.isInfinite, "
					+ "p.timeEnd, p.anonCreator, u.email "
					+ "FROM Polls p, Users u "
					+ "WHERE p.creatorID = u.userID;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				String title = rs.getString("p.title");		//poll title
				int pollID = rs.getInt("p.pollID");			//poll ID
				String image = rs.getString("p.image");			//image url
				ArrayList<String> tags = getPollTags(pollID);	//poll tags
				ArrayList<String> options = getOptions(pollID);	//poll options	
				HashMap<String, Integer> numVotes = getPollVotes(pollID);
				int numViews = rs.getInt("p.numViews");
				ArrayList<CommentAction> comments = getComments(pollID);	//a list of commentActions associated with the enitity
				Boolean isInfinite = rs.getBoolean("p.isInfinite");
				Timestamp endTimestamp = rs.getTimestamp("p.timeEnd");
				Calendar timeEnd = timestampToCalendar(endTimestamp);
				String creatorEmail = rs.getString("u.email");
				Boolean anonCreator = rs.getBoolean("p.anonCreator");
				
				//create the poll and add it to the list of polls
				Poll poll = new Poll(title, pollID, tags, options, numViews, comments, isInfinite, 
						timeEnd, image, numVotes, creatorEmail, anonCreator);
				polls.add(poll);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getPolls: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getPolls: " + ae.getMessage());
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
		Timestamp timeEnd = null;
		
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
			ps = conn.prepareStatement("SELECT numViews, timeEnd "
					+ "FROM " + tableName + " "
					+ "WHERE " + idType + " = '" + subjectID + "';");

			rs = ps.executeQuery();
			rs.next();
			numViews = rs.getInt("numViews");
			timeEnd = rs.getTimestamp("timeEnd");
		} catch (SQLException sqle) {
			System.out.println("sqle in addNumView: " + sqle.getMessage());
		}

		numViews++;

		// update the numViews
		try {
			ps = conn.prepareStatement("UPDATE " + tableName + " " 
					+ "SET numViews = " + numViews + ", " + "timeEnd = '" + timeEnd + "' "
					+ "WHERE " + idType + " = '" + subjectID + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in addNumView: " + sqle.getMessage());
		}
		
	}//addNumView()
	
	
	//adds 1 to the rating of an entity
	public void upVoteEntity(int entityID){

		int rating = 0;
		Timestamp timeEnd = null;

		
		// get the numViews for the entity or poll
		try {
			ps = conn.prepareStatement("SELECT rating, timeEnd "
					+ "FROM Entities "
					+ "WHERE entityID = '" + entityID + "';");

			rs = ps.executeQuery();
			rs.next();
			rating = rs.getInt("rating");
			timeEnd = rs.getTimestamp("timeEnd");
		} catch (SQLException sqle) {
			System.out.println("sqle in upVoteEntity: " + sqle.getMessage());
		}

		rating++;

		// update the numViews
		try {
			ps = conn.prepareStatement("UPDATE Entities " 
					+ "SET rating = " + rating + ", " + "timeEnd = '" + timeEnd + "' "
					+ "WHERE entityID = '" + entityID + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in upVoteEntity: " + sqle.getMessage());
		}
	}//upVoteEntity()
	
	
	//subtracts 1 from the rating of an entity
	public void downVoteEntity(int entityID){
		int rating = 0;
		Timestamp timeEnd = null;

		
		// get the numViews for the entity or poll
		try {
			ps = conn.prepareStatement("SELECT rating, timeEnd "
					+ "FROM Entities "
					+ "WHERE entityID = '" + entityID + "';");

			rs = ps.executeQuery();
			rs.next();
			rating = rs.getInt("rating");
			timeEnd = rs.getTimestamp("timeEnd");
		} catch (SQLException sqle) {
			System.out.println("sqle in downVoteEntity: " + sqle.getMessage());
		}

		rating--;

		// update the numViews
		try {
			ps = conn.prepareStatement("UPDATE Entities " 
					+ "SET rating = " + rating + ", " + "timeEnd = '" + timeEnd + "' "
					+ "WHERE entityID = '" + entityID + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in downVoteEntity: " + sqle.getMessage());
		}
	}//downVoteEntity()
	
	
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
	
	
	//returns the number of polls + entities in teh database
	public int getNumThings(){
		int numThings = 0;
		
		try{
			ps = conn.prepareStatement("SELECT p.title "
					+ "FROM Polls p;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				numThings++;
			}
			
			ps = conn.prepareStatement("SELECT e.title "
					+ "FROM Entities e;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				numThings++;
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getNumThings: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getNumThings: " + ae.getMessage());
		}
		
		return numThings;
	}//getNumThings()
	
	
	//returns null if user has not rated or voted
	//returns the Action if user has already rated or voted
	public Action userRatedOrVoted(int subjectID, String email){
		
		Action action = null;
		int userID = this.getUserID(email);
		
		try{
			ps = conn.prepareStatement("SELECT type, actionValue "
					+ "FROM Activities "
					+ "WHERE userID = " + userID + " "
					+ "AND subjectID = " + subjectID + ";");
			rs = ps.executeQuery();
			
			while(rs.next()){
				String type = rs.getString("type");
				if(type.equals("PollAction")){
					String option = rs.getString("actionValue");
					action = new PollAction(false, email, subjectID, option);
					break;
				}
				else if(type.equals("RatingAction")){
					String actionValue = rs.getString("actionValue");
					Boolean isUpVote = true;
					if(actionValue.equals("downVote")){ isUpVote = false; }
					action = new RatingAction(false, email, subjectID, isUpVote);
				}
			}
			
		} catch(SQLException sqle){
			System.out.println("sqle in userRatedOrVoted: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in userRatedOrVoted: " + ae.getMessage());
		}
		
		return action;
	}//userRatedOrVoted()
	
	
	//add a new follower 
	//follower is the person who is following someone
	//following is the person that follower is follwing
	public void newFollower(String follower, String following){
		int followerID = getUserID(follower);
		int followingID = getUserID(following);
		
		try{
			ps = conn.prepareStatement("INSERT INTO Following(follower, following)"
					+ "VALUES('" + followerID + "', '" + followingID + "')");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addUser: " + sqle.getMessage());
		}
	}//newFollower()
	
	//unfollow a user 
	//follower is the person who is following someone
	//following is the person that follower is follwing
	public void unfollow(String follower, String following){
		int followerID = getUserID(follower);
		int followingID = getUserID(following);
		
		try{
			ps = conn.prepareStatement("DELETE FROM Following WHERE following=" + followingID + " AND follower=" + followerID + ";");
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in unfollow: " + sqle.getMessage());
		}
	}//unfollow()
	
	
	//get the followers of the user with email passed
	public ArrayList<String> getFollowers(String email){
		ArrayList<String> followers = new ArrayList<String>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		int userID = getUserID(email);

		try {
			p = conn.prepareStatement("SELECT u.email " 
					+ "FROM Users u, Following f " 
					+ "WHERE f.following = " + userID + " "
					+ "AND f.follower = u.userID");
			r = p.executeQuery();
			while (r.next()) {
				String follower = r.getString("email");
				followers.add(follower);
			}
		} catch (SQLException sqle) {
			System.out.println("sqle in getFollowers: " + sqle.getMessage());
		}

		return followers;
	}//getFollowers()
	
	
	// get user's following list
	public ArrayList<String> getFollowing(String email) {
		ArrayList<String> following = new ArrayList<String>();
		
		//need new prepare statement and result set because this function will be called in
		//the middle of parsing through global result set. If it used the same set, this 
		//function would overwrite the results from the last call to the database
		PreparedStatement p = null;
		ResultSet r = null;
		
		int userID = getUserID(email);

		try {
			p = conn.prepareStatement("SELECT u.email " 
					+ "FROM Users u, Following f " 
					+ "WHERE f.follower = " + userID + " " 
					+ "AND f.following = u.userID");
			r = p.executeQuery();
			while (r.next()) {
				String uFollowing = r.getString("email");
				following.add(uFollowing);
			}
		} catch (SQLException sqle) {
			System.out.println("sqle in getUserFollowers: " + sqle.getMessage());
		}

		return following;
	}// getUserFollowing()
	
	
	//edit a comment
	public void editCommment(int subjectID, String oldComment, String newComment){
		
		// update the comment
		try {
			ps = conn.prepareStatement("UPDATE Activities " 
					+ "SET actionValue = '" + newComment + "' "
					+ "WHERE subjectID = " + subjectID + " "
					+ "AND actionValue = '" + oldComment + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in editComment: " + sqle.getMessage());
		}
		
	}//editComment()
	
	
	//edit a user's rating on an entity
	public void editRating(int subjectID, String userEmail){
		int userID = getUserID(userEmail);
		Boolean upVote = true;
		
		//get the current vote - either upVote or downVote
		try {
			ps = conn.prepareStatement("SELECT actionValue " 
					+ "FROM Activities " 
					+ "WHERE subjectID = " + subjectID + " " 
					+ "AND type = 'RatingAction' "
					+ "AND userID = " + userID + " ");
			rs = ps.executeQuery();
			
			rs.next();
			String value = rs.getString("actionValue");
			if(value.equals("downVote")){
				upVote = false;
			}
			
		} catch (SQLException sqle) {
			System.out.println("sqle in editRating: " + sqle.getMessage());
		}
		
		
		//now change the value of the action
		upVote = !upVote;
		//to change action value in activities table
		String newActionValue = "upVote";
		if(!upVote){ newActionValue = "downVote"; }

		
		// update the rating action in activities table
		try {
			ps = conn.prepareStatement("UPDATE Activities " 
					+ "SET actionValue = '" + newActionValue + "' "
					+ "WHERE subjectID = " + subjectID + " " 
					+ "AND type = 'RatingAction' "
					+ "AND userID = " + userID + " ");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in editRating: " + sqle.getMessage());
		}
		
		//update the rating on the entity
		updateRating(subjectID, upVote);
	}//editrating()
	
	
	//update the rating of an entity
	private void updateRating(int subjectID, Boolean upVote){
		int rating = 0;
		Timestamp timeEnd = null;
		System.out.println("update rating timeEnd: " + timeEnd);
		
		// get the current rating for the entity
		try {
			ps = conn.prepareStatement("SELECT rating, timeEnd "
					+ "FROM Entities "
					+ "WHERE entityID = '" + subjectID + "';");

			rs = ps.executeQuery();
			rs.next();
			rating = rs.getInt("rating");
			timeEnd = rs.getTimestamp("timeEnd");
		} catch (SQLException sqle) {
			System.out.println("sqle in updateRating: " + sqle.getMessage());
		}

		//change the rating as necessary
		if(upVote){
			//add 1 to go back to neutral, one more for new upvote
			rating += 2;
		}
		else{
			//subtract 1 to go back to neutral, one more for new downvote
			rating -= 2;
		}

		// update the rating
		try {
			ps = conn.prepareStatement("UPDATE Entities " 
					+ "SET timeEnd = '" + timeEnd + "', " + "rating = '" + rating + "' "
					+ "WHERE entityID = '" + subjectID + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in updateRating: " + sqle.getMessage());
		}
	}//updateRating()
	
	
	//update the action corresponding with a user's vote
	public void editVote(int subjectID, String userEmail, String newVote){
		int userID = getUserID(userEmail);
		String oldVote = "";
		
		//get the current choice
		try {
			ps = conn.prepareStatement("SELECT actionValue " 
					+ "FROM Activities " 
					+ "WHERE subjectID = " + subjectID + " " 
					+ "AND type = 'PollAction' "
					+ "AND userID = " + userID + " ");
			rs = ps.executeQuery();
			
			rs.next();
			oldVote = rs.getString("actionValue");
			
		} catch (SQLException sqle) {
			System.out.println("sqle in editRating: " + sqle.getMessage());
		}
		
		// update the poll action in activities table
		try {
			ps = conn.prepareStatement("UPDATE Activities " 
					+ "SET actionValue = '" + newVote + "' "
					+ "WHERE subjectID = " + subjectID + " " 
					+ "AND type = 'PollAction' "
					+ "AND userID = " + userID + " ");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in editVote: " + sqle.getMessage());
		}
		
		//decrement the vote count on the old choice
		updateOptionCount(subjectID, oldVote, -1);
		//increment the vote count on the new choice
		updateOptionCount(subjectID, newVote, 1);
	}//editVote()
	
	
	//update the number of votes for the old option and the new option
	private void updateOptionCount(int pollID, String option, int numDif){
		int numVotes = 0;
		
		// get the current numVotes for the option
		try {
			ps = conn.prepareStatement("SELECT numVotes "
					+ "FROM Options "
					+ "WHERE pollID = " + pollID + " "
					+ "AND title = '" + option + "';");

			rs = ps.executeQuery();
			rs.next();
			numVotes = rs.getInt("numVotes");
		} catch (SQLException sqle) {
			System.out.println("sqle in updateOptionCount: " + sqle.getMessage());
		}

		//change the numVotes as necessary
		numVotes += numDif;

		// update the numVotes
		try {
			ps = conn.prepareStatement("UPDATE Options " 
					+ "SET numVotes = " + numVotes + " "
					+ "WHERE pollID = " + pollID + " "
					+ "AND title = '" + option + "';");
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("sqle in updateOptionCount: " + sqle.getMessage());
		}
	}//updateOptionCount()
	
	
	//delete a poll
	public void deletePoll(int pollID){
		
		try{
			//delete activities for the entity
			ps = conn.prepareStatement("DELETE FROM Activities "
					+ "WHERE subjectID = " + pollID + ";" );
			ps.execute();
			//delete poll options
			ps = conn.prepareStatement("DELETE FROM Options "
					+ "WHERE pollID = " + pollID + ";" );
			ps.execute();
			//delete poll tags
			ps = conn.prepareStatement("DELETE FROM PollTags "
					+ "WHERE pollID = " + pollID + ";" );
			ps.execute();
			//delete poll
			ps = conn.prepareStatement("DELETE FROM Polls "
					+ "WHERE pollID = " + pollID + ";" );
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in deletePoll: " + sqle.getMessage());
		}
	}//deletePoll()
	
	
	//delete an entity
	public void deleteEntity(int entityID){
		
		try{
			//delete activities for the entity
			ps = conn.prepareStatement("DELETE FROM Activities "
					+ "WHERE subjectID = " + entityID + ";" );
			ps.execute();
			//delete entity tags
			ps = conn.prepareStatement("DELETE FROM EntityTags "
					+ "WHERE entityID = " + entityID + ";" );
			ps.execute();
			//delete poll
			ps = conn.prepareStatement("DELETE FROM Entities "
					+ "WHERE entityID = " + entityID + ";" );
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in deleteEntity: " + sqle.getMessage());
		}
	}//deletePoll()

	
	//add a new blog
	public void addBlog(Blog newBlog){
		String title = newBlog.getTitle();
		String description = newBlog.getDescription();
		String content = newBlog.getContent();
		String dateCreated = newBlog.getDateCreated();
		String image = newBlog.getImage();
		String creator = newBlog.getCreator();
		int creatorID = getUserID(creator);
		
		try{
			ps = conn.prepareStatement("INSERT INTO Blogs(creatorID, title, description, image, content, dateCreated) "
					+ "VALUES(" + creatorID + ", '" + title + "', '" + description + "', '" + image + "', '" 
					+ content + "', '11/11/2017')");
			
			ps.execute();
		} catch(SQLException sqle){
			System.out.println("sqle in addBlog: " + sqle.getMessage());
		}
		
	}//addBlog()
	
	
	//get all the blogs
	public ArrayList<Blog> getBlogs(){
		ArrayList<Blog> blogs = new ArrayList<Blog>();
		
		try{			
			ps = conn.prepareStatement("SELECT b.blogID, b.title, b.description, b.content, b.dateCreated, "
					+ "b.image, u.email "
					+ "FROM Blogs b, Users u "
					+ "WHERE b.creatorID = u.userID;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				int blogID = rs.getInt("blogID");
				String title = rs.getString("b.title");		//blog title
				String description = rs.getString("b.description");	//blog description
				String content = rs.getString("b.content");	//blog content
				String dateCreated = rs.getString("b.dateCreated");	//blog description
				String image = rs.getString("b.image");			//image url
				String creator = rs.getString("u.email");	//blog description
				ArrayList<CommentAction> comments = getComments(blogID);
				
				comments = getComments(blogID);
				
				//create the blog and add it to the list of blogs
				Blog blog = new Blog(blogID, title, description, creator, image, content, dateCreated, comments);
				blogs.add(blog);
			}
		} catch(SQLException sqle){
			System.out.println("sqle in getBlogs: " + sqle.getMessage());
		} catch(ArithmeticException ae){
			System.out.println("ae in getBLogs: " + ae.getMessage());
		}
		
		return blogs;
	}//getBlogs()

}