package masterSteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.TestContext;
import utils.EmailValidation;

public class MasterSteps {
	protected TestContext testContext;
	Logger logger;

	public MasterSteps() {
		testContext = new TestContext();
		logger = LoggerFactory.getLogger(MasterSteps.class);
	}

	protected void searchUserName(String userName) {
		testContext.getCommentsManager().setUserId(testContext.getEndPoints().getUserId(userName));
	}

	protected void searchPosts() {
		int userId = testContext.getCommentsManager().getUserId();
		testContext.getCommentsManager().setUserPosts(testContext.getEndPoints().getUserPosts(userId));
	}

	protected void searchCommentsOnPosts() {
		List<Integer> commentIdsOnPosts = new ArrayList<Integer>();
		List<Integer> userPosts = testContext.getCommentsManager().getUserPosts();
		userPosts.forEach(postId -> {
			commentIdsOnPosts.addAll(testContext.getEndPoints().getPostCommentIds(postId));
		});
		testContext.getCommentsManager().setCommentIdsOnPosts(commentIdsOnPosts);
	}

	protected void getEmailIdsForComments() {
		List<String> emailIdsFromComments = new ArrayList<String>();
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getCommentIdsOnPosts();
		commentIdsOnPosts.forEach(commentId -> {
			emailIdsFromComments.addAll(testContext.getEndPoints().getEmailIdsFromComments(commentId));
		});
		testContext.getCommentsManager().setEmailIdsFromComments(emailIdsFromComments);
	}

	protected void getNamesForComments() {
		List<String> namesFromComments = new ArrayList<String>();
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getCommentIdsOnPosts();
		commentIdsOnPosts.forEach(commentId -> {
			namesFromComments.addAll(testContext.getEndPoints().getNamesFromComments(commentId));
		});
		testContext.getCommentsManager().setNamesFromComments(namesFromComments);
	}

	protected void getBodysForComments() {
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getCommentIdsOnPosts();
		List<String> bodysFromComments = new ArrayList<String>();
		commentIdsOnPosts.forEach(commentId -> {
			bodysFromComments.addAll(testContext.getEndPoints().getBodysFromComments(commentId));
		});
		testContext.getCommentsManager().setBodysFromComments(bodysFromComments);
	}

	protected void validateFormatOfEmailIds() {
		List<Integer> invalidEmailIds = new ArrayList<Integer>();
		List<String> emailIdsFromComments = testContext.getCommentsManager().getEmailIdsFromComments();

		emailIdsFromComments.forEach(emailId -> {
			// if (!EmailValidation.isValidEmail(emailId)) {
			if (EmailValidation.isInvalidEmail(emailId)) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts()
						.get(emailIdsFromComments.indexOf(emailId));
				invalidEmailIds.add(commentId);
			}
		});

		System.out.println(invalidEmailIds.size());
		if (invalidEmailIds.size() > 0) {
			logger.info("CommentIds where values where invalid formated Email provided..");
			invalidEmailIds.forEach(id -> {
				logger.info(id.toString());
			});
		}
	}

	protected void validateIfFieldEmpty(List<String> textToValidate, String text) {
		List<Integer> blankText = new ArrayList<Integer>();
		textToValidate.forEach(textValue -> {
			 if (textValue.isEmpty()) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts()
						.get(textToValidate.indexOf(textValue));
				blankText.add(commentId);
			}
		});

		System.out.println(blankText.size());
		if (blankText.size() > 0) {
			logger.info("CommentIds where values are empty for " + text);
			blankText.forEach(id -> {
				logger.info(id.toString());
			});
		}
	}

	protected void validateLengthOfCommentField(List<String> textToValidate, int commentLength, String nameOfField) {
		List<Integer> blankText = new ArrayList<Integer>();
		textToValidate.forEach(textValue -> {
			if (textValue.length() < commentLength) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts()
						.get(textToValidate.indexOf(textValue));
				blankText.add(commentId);
			}
		});

		System.out.println(blankText.size());
		if (blankText.size() > 0) {
			logger.info("CommentIds where length of " + nameOfField + " less than " + commentLength);
			blankText.forEach(id -> {
				logger.info(id.toString());
			});
		}
	}
}
