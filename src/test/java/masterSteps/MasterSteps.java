package masterSteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.TestContext;


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
		List<String> invalidEmailIds = new ArrayList<String>();
		List<String> emailIdsFromComments = testContext.getCommentsManager().getEmailIdsFromComments();
		
		emailIdsFromComments.forEach(emailId -> {
			// if (!EmailValidation.isValidEmail(emailId)) {
			if (emailId.endsWith("net")) {
				invalidEmailIds.add(emailId);
			}
		});
		if (invalidEmailIds.size() > 0) {
			invalidEmailIds.forEach(emailId -> {
				System.out.println("Email Address ending with .net:- " + emailId);
			});
		}
	}

	protected void validateIfTextEmpty(List<String> textToValidate, String text) {
		List<Integer> blankText = new ArrayList<Integer>();
		textToValidate.forEach(textValue -> {
			// if (textValue.isEmpty()) {
			if (textValue.length() < 6) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts().get(textToValidate.indexOf(textValue));
				blankText.add(commentId);
			}
		});

		if (blankText.size() > 0) {
			logger.info("CommentIds where values are empty for "+ text);
			blankText.forEach(value -> {
				logger.info(value.toString());
				
			});
		}
	}

	protected void validateLengthOfComment(List<String> textToValidate, int commentLength) {
		List<String> blankText = new ArrayList<String>();
		textToValidate.forEach(textValue -> {
			if (textValue.length() < commentLength) {
				blankText.add(textValue);
			}
		});

		if (blankText.size() > 0) {
			blankText.forEach(name -> {
				System.out.println("Comments not less than " + commentLength + ":- " + name);
			});
		}
	}
}
