package cucumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import apiMachine.EndPoints;
import masterSteps.MasterSteps;
import utils.CommentsManager;

public class TestContext {
	private EndPoints endPoints;
	private CommentsManager commentsManager;
	private Logger logger;

	public TestContext() {
		commentsManager = new CommentsManager();
		endPoints = new EndPoints();
		logger = LoggerFactory.getLogger(MasterSteps.class);
	}

	public EndPoints getEndPoints() {
		return endPoints;
	}

	public CommentsManager getCommentsManager() {
		return commentsManager;
	}

	public Logger getLogger() {
		return logger;
	}

}
