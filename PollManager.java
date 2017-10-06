import java.util.List;



public class PollManager {

	private List<Poll> polls; // list of current polls being displayed
	private int numPolls; 
	
	
	public Poll getPoll(int pollID) {
		Poll poll = polls.get(pollID);
		return poll; 
	}
	
	public void addPoll(Poll newPoll) {
		polls.add(newPoll);
		numPolls++;
	}
	
	private List<Poll> sortByPopularity() {
		List<Poll> sortedList = null;
		sortedList.add(polls.get(0));
		for (int i = 1; i < polls.size(); i++) {
			Poll unsortedPoll = polls.get(i);
			int insertPoint = (sortedList.size()/2);

			if (unsortedPoll.getNumVotes() < sortedList.get(insertPoint).getNumVotes()) {
				while (unsortedPoll.getNumVotes() < sortedList.get(insertPoint).getNumVotes()) {
					insertPoint++; 
				}
				sortedList.add(insertPoint, unsortedPoll);
			}
			else if (unsortedPoll.getNumVotes() > sortedList.get(insertPoint).getNumVotes()){
				while (unsortedPoll.getNumVotes() > sortedList.get(insertPoint).getNumVotes()) {
					insertPoint--; 
				}
				sortedList.add(insertPoint, unsortedPoll);
			}
			else {
				sortedList.add(insertPoint, unsortedPoll);
			}
		}
		return sortedList;
	}
	
	public void newVote(int pollID, String choice) {
		// if user hasn't already voted on this poll
		for (int i = 0; i < polls.size(); i++) {
			Poll poll = polls.get(i);
			if (poll.getID() == pollID) {
				poll.vote(choice);
			}
		}
	}
	
	public void newComment(int pollID, String newComment) {
		for (int i = 0; i < polls.size(); i++) {
			Poll poll = polls.get(i);
			// turn string parameter into commentAction
			//commentActions comment = newComment; 
	
			if (poll.getID() == pollID) {
				
			}
		}
	}
	
	public List<Poll> searchPolls(String search) {
		List<Poll> searchResults = null; 
		for (int i = 0; i < polls.size(); i++) {
			String pollTitle = polls.get(i).getTitle();
			if (pollTitle.contains(search)) {
				searchResults.add(polls.get(i));
			}
		}
		return searchResults; 
	}
	
	public void addView(int pollID) {
		Poll poll = polls.get(pollID);
		poll.addView();
	}
	public int getNumPolls() {
		return numPolls;
	}
}
