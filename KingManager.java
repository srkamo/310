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
	private User curUser;
	private ArrayList<Object> allItemsDisplayed;
	
	
	public KingManager(){
		database  = new Database();
		entityManager = new EntityManager();
		pollManager = new PollManager();
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
		System.out.println("wya " + search);
		ArrayList<Entity> entities = database.searchForEntities(search);
		System.out.println("entities size: " + entities.size());
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
		
		System.out.println("all size: " + all.size());
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
	
	
/*----------------Database------------------------------- */
	public User getUser(String email){
		return database.getUser(email);
	}
	
	public void addUser(User newUser){
		database.addUser(newUser);
	}	

/*----------------EntityManager------------------------------- */
	public Entity getEntity(int entityID){
		return entityManager.getEntity(entityID);
	}
	
	public void addEntity(Entity newEntity){
		database.addEntity(newEntity);
		allItemsDisplayed.add(newEntity);
		entityManager.addEntity(newEntity);
		ids++;
	}
	
	public void newRating(int entityID, Boolean upVote, String userEmail, Boolean isAnon) {
		entityManager.newRating(entityID, upVote);
		Action ratingAction = new RatingAction(isAnon, userEmail, entityID, upVote);
		Action action = new Action(isAnon, userEmail, entityID);
		database.addAction(ratingAction);
	}
	
	public void newEntityComment(int entityID, String userEmail, String newComment, Boolean isAnon) { 
		entityManager.newComment(entityID, userEmail, newComment);
		Action commentAction = new CommentAction(isAnon, userEmail, entityID, newComment);
		Action action = new Action(isAnon, userEmail, entityID);
		database.addAction(commentAction);
	}
	
	public void addEntityView(int entityID) {
		entityManager.addView(entityID);
		database.addNumView(entityID, "Entity");
	}

/*----------------PollManager------------------------------- */
	public Poll getPoll(int pollID){
		return pollManager.getPoll(pollID);
	}
	
	public void addPoll(Poll newPoll){
		database.addPoll(newPoll);
		allItemsDisplayed.add(newPoll);
		pollManager.addPoll(newPoll);
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
		pollManager.newComment(pollID, userEmail, newComment);
		Action commentAction = new CommentAction(isAnon, userEmail, pollID, newComment);
		Action action = new Action(isAnon, userEmail, pollID);
		database.addAction(commentAction);
	}
	
	
	//returns the number of items to use as an id
		public int getIds(){
			return ids;
		}//getIds()
		
		public int getCurrId(){
			return ids;
		}
		
		
		public Boolean isPollExpired(int id){
			Poll currPoll = this.getPoll(id);
			Calendar curCal = currPoll.getTimeEnd();
			Calendar now = Calendar.getInstance();
			return curCal.before(now);
		}
		
		public Boolean isEntityExpired(int id){
			Entity currEntity = this.getEntity(id);
			Calendar curCal = currEntity.getTimeEnd();
			Calendar now = Calendar.getInstance();
			return curCal.before(now);
		}
	
}