package Business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import Database.Database;

public class KingManager {

	
	public int ids;
	private Database database;
	private EntityManager entityManager;
	private PollManager pollManager;
	private BlogManager blogManager;
	private User curUser;
	private ArrayList<Object> allItemsDisplayed;
	
	
	public KingManager(){
		database  = new Database();
		entityManager = new EntityManager();
		pollManager = new PollManager();
		blogManager = new BlogManager();
		ids = database.getNumThings();
		curUser = null;
		allItemsDisplayed = new ArrayList<Object>();
	}
	
	
	public User getCurUser() {
		return curUser;
	}

	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}
	
	
	public ArrayList<Object> search(String search){
		ArrayList<Entity> entities = database.searchForEntities(search);
		entityManager.setEntityList(entities);
		ArrayList<Poll> polls = database.searchForPolls(search);
		pollManager.setPollList(polls);
		
		ArrayList<Object> all = new ArrayList<Object>();
		all.addAll(entities);
		all.addAll(polls);
		
		Collections.sort(all, new Comparator<Object>() {
			public int compare(Object o1, Object o2){
				int numViews1 = 0, numViews2 = 0;
				if(o1 instanceof Entity){
					numViews1 = ((Entity)o1).getNumViews();
				}
				else if(o1 instanceof Poll){
					numViews1 = ((Poll)o1).getNumViews();
				}
				
				if(o2 instanceof Entity){
					numViews2 = ((Entity)o2).getNumViews();
				}
				else if(o2 instanceof Poll){
					numViews2 = ((Poll)o2).getNumViews();
				}

				return numViews2 - numViews1;
			}
		});
		
		allItemsDisplayed = all;
		return all;
	}//search()
	
	
	//returns all items that should be displayed
	public ArrayList<Object> getAllItemsDisplayed(){
		return allItemsDisplayed;
	}//getAllItemsDisplayed()
	
	
	//get the list of actions performed by the current user
	public ArrayList<Action> getCurrUserActions(){
		ArrayList<Action> userActions = database.getUserActions(curUser.getEmail());
		return userActions;
	}
	
	//get the list of actions performed by the user passed
	public ArrayList<Action> getUserActions(String email){
		ArrayList<Action> userActions = database.getUserActions(email);
		return userActions;
	}
	
	
	//retruns null if user has not rated or voted
	//otherwise returns thw action associated with the rating or voting
	public Action userRatedOrVoted(int subjectID, String email){
		return database.userRatedOrVoted(subjectID, email);
	}//userRatedOrVoted()
	
	
	//returns the number of items to use as an id
	public int getIds(){
		return ids;
	}//getIds()
	
	public int getCurrId(){
		return ids;
	}
	
	
/*----------------Database------------------------------- */
	public User getUser(String email){
		return database.getUser(email);
	}
	
	public void addUser(User newUser){
		database.addUser(newUser);
	}	
	
	public void editComment(int subjectID, String oldComment, String newComment){
		database.editCommment(subjectID, oldComment, newComment);
		pollManager.setPollList(database.getPolls());
		entityManager.setEntityList(database.getEntities());
	}
	
	public void editRating(int subjectID, String email){
		database.editRating(subjectID, email);
		entityManager.setEntityList(database.getEntities());
	}
	
	public void editVote(int subjectID, String email, String newVote){
		database.editVote(subjectID, email, newVote);
		pollManager.setPollList(database.getPolls());
	}
	
	public void deletePoll(int pollID){
		database.deletePoll(pollID);
		pollManager.setPollList(database.getPolls());
	}
	
	public void deleteEntity(int entityID){
		database.deleteEntity(entityID);
		entityManager.setEntityList(database.getEntities());
	}
	
	public void followUser(String userToFollow) {
		User toFollow = this.getUser(userToFollow); 
		database.newFollower(curUser.getEmail(), userToFollow);
		curUser = database.getUser(curUser.getEmail());
	}
	
	public Boolean alreadyFollows(String userToFollow) {
		User toFollow = this.getUser(userToFollow); 
		Boolean doesFollow = false; 
		
		if (curUser.getFollowing().contains(userToFollow)) {
			doesFollow = true; 
		}
		
		return doesFollow;
	}
	
	public void unfollowUser(String userToUnfollow) {
		User toUnfollow = this.getUser(userToUnfollow); 
		database.unfollow(curUser.getEmail(), userToUnfollow);
		curUser = database.getUser(curUser.getEmail());
	}	

/*----------------EntityManager------------------------------- */
	public Entity getEntity(int entityID){
		return entityManager.getEntity(entityID);
	}
	
	public Entity getEntityByTitle (String entityTitle){
		return entityManager.getEntityByTitle(entityTitle);
	}
	
	public void addEntity(Entity newEntity){
		database.addEntity(newEntity);
		allItemsDisplayed.add(newEntity);
		//entityManager.addEntity(newEntity);
		entityManager.setEntityList(database.getEntities());
		ids++;
	}
	
	public void newRating(int entityID, Boolean upVote, String userEmail, Boolean isAnon) {
		entityManager.newRating(entityID, upVote);
		Action ratingAction = new RatingAction(isAnon, userEmail, entityID, upVote);
		Action action = new Action(isAnon, userEmail, entityID);
		
		//add something here to update database with new rating value
		if(upVote){
			database.upVoteEntity(entityID);
		}
		else{
			database.downVoteEntity(entityID);
		}
		
		database.addAction(ratingAction);
	}
	
	public void newEntityComment(int entityID, String userEmail, String newComment, Boolean isAnon) { 
		entityManager.newComment(entityID, userEmail, newComment, isAnon);
		Action commentAction = new CommentAction(isAnon, userEmail, entityID, newComment);
		Action action = new Action(isAnon, userEmail, entityID);
		database.addAction(commentAction);
	}
	
	public void addEntityView(int entityID) {
		entityManager.addView(entityID);
		database.addNumView(entityID, "Entity");
	}
	
	public Boolean isEntityExpired(int id){
		Entity currEntity = this.getEntity(id);
		Calendar curCal = currEntity.getTimeEnd();
		Calendar now = Calendar.getInstance();
		return curCal.before(now);
	}

/*----------------PollManager------------------------------- */
	public Poll getPoll(int pollID){
		return pollManager.getPoll(pollID);
	}
	
	public Poll getPollByTitle (String pollTitle){
		return pollManager.getPollByTitle(pollTitle);
	}
	
	public void addPoll(Poll newPoll){
		database.addPoll(newPoll);
		allItemsDisplayed.add(newPoll);
		//pollManager.addPoll(newPoll);
		pollManager.setPollList(database.getPolls());
		ids++;
	}
	
	public void addPollView(int pollID) {
		pollManager.addView(pollID);
		database.addNumView(pollID, "Poll");
	}
	
	public void newVote(int pollID, String choice, Boolean isAnon, String userEmail) {
		pollManager.newVote(pollID, choice);
		Action pollAction = new PollAction(isAnon, userEmail, pollID, choice);
		Action action = new Action(isAnon, userEmail, pollID);
		database.addAction(pollAction);
	}
	
	public void newPollComment(int pollID, String userEmail, String newComment, Boolean isAnon) { 
		pollManager.newComment(pollID, userEmail, newComment, isAnon);
		Action commentAction = new CommentAction(isAnon, userEmail, pollID, newComment);
		Action action = new Action(isAnon, userEmail, pollID);
		database.addAction(commentAction);
	}
	
	
	public Boolean isPollExpired(int id){
		Poll currPoll = this.getPoll(id);
		Calendar curCal = currPoll.getTimeEnd();
		Calendar now = Calendar.getInstance();
		return curCal.before(now);
	}
	
	
	
	/*----------------BlogManager------------------------------- */
	public Blog getBlog(String title){
		return blogManager.getBlog(title);
	}
	
	public void addBlog(Blog newBlog){
		database.addBlog(newBlog);
		blogManager.setBlogList(database.getBlogs());
	}
	
	//returns all blogs for blogFeed.jsp
	public ArrayList<Blog> getAllBlogs(){
		return blogManager.getAllBlogs();
	}//getAllItemsDisplayed()
}