
import java.util.ArrayList;


public class EntityManager {

	private ArrayList<Entity> entities; //list of current entities being displayed
	private int numEntities; 
	
	
	public Entity getEntity(int entityID) {
		
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i); 
			if (entity.getID() == entityID) {
				return entity; 
			}
		}
		return null;
	}
	
	public void addEntity(Entity newEntity) {
		entities.add(newEntity);
		numEntities++;
		
	}
	
	public ArrayList<Entity> sortByPopularity() {
		ArrayList<Entity> sortedList = new ArrayList<Entity>();
		sortedList.add(entities.get(0));
		for (int i = 1; i < entities.size(); i++) {
			Entity unsortedEntity = entities.get(i);
			int insertPoint = (sortedList.size()/2);

			if (unsortedEntity.getNumViews() < sortedList.get(insertPoint).getNumViews()) {
				while (unsortedEntity.getNumViews() < sortedList.get(insertPoint).getNumViews()) {
					insertPoint++; 
				}
				sortedList.add(insertPoint, unsortedEntity);
			}
			else if (unsortedEntity.getNumViews() > sortedList.get(insertPoint).getNumViews()){
				while (unsortedEntity.getNumViews() > sortedList.get(insertPoint).getNumViews()) {
					insertPoint--; 
				}
				sortedList.add(insertPoint, unsortedEntity);
			}
			else {
				sortedList.add(insertPoint, unsortedEntity);
			}
		}
		return sortedList;
	}
	
	public void newRating(int entityID, Boolean upVote) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.getID() == entityID) {
				if (upVote) {
					entity.upVote();
				}
				else {
					entity.downVote();
				}
			}
		}
	}
	
	public void newComment(int entityID, String userEmail, String newComment) { // added userEmail as a parameter
		Boolean isAnon = false;
		CommentAction comment = new CommentAction(isAnon, userEmail, entityID, newComment);
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.getID() == entityID) {
				entity.addComment(comment);
			}
		}	
	}
	
	public ArrayList<Entity> searchEntities(String search) {
		ArrayList<Entity> searchResults = new ArrayList<Entity>(); 
		for (int i = 0; i < entities.size(); i++) {
			String entityTitle = entities.get(i).getTitle();
			String entityDescrip = entities.get(i).getDescription();
			if (entityTitle.contains(search) || entityDescrip.contains(search)) {
				searchResults.add(entities.get(i));
			}
		}
		return searchResults;
	}
	
	public void addView(int entityID) {
		Entity entity = entities.get(entityID);
		entity.addView();
	}
	public int getNumEntities() {
		return numEntities; 
	}
}
