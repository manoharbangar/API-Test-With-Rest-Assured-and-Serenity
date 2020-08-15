package cucumber;

import apiMachine.EndPoints;
import utils.CommentsManager;

public class TestContext {
	// Variable declared to hold BASE URI
	private EndPoints endPoints;
	private CommentsManager commentsManager;

	public TestContext() {
		commentsManager = new CommentsManager();
		endPoints = new EndPoints();
	}

	public EndPoints getEndPoints() {
		return endPoints;
	}

	public CommentsManager getCommentsManager() {

		return commentsManager;
	}


}
