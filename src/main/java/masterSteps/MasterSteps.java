package masterSteps;

import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import cucumber.TestContext;
import utils.EmailValidation;

public class MasterSteps {
	protected TestContext testContext;
	protected Logger logger;

	public MasterSteps() {
		testContext = new TestContext();
		logger = testContext.getLogger();
	}

	// searchUserName method will request - Get/users to get userId based on
	// username provided
	protected void searchUserName(String userName) {
		int userId = testContext.getEndPoints().getUserId(userName);
		if (userId > 0) {
			testContext.getCommentsManager().setUserId(userId);
		} else {
			logger.info("UserId not found for given username...");
			Assert.fail();
		}
	}

	// searchPosts method will request - Get/posts to get userId based on userId
	// provided
	protected void searchPosts() {
		int userId = testContext.getCommentsManager().getUserId();
		List<Integer> userPosts = testContext.getEndPoints().getUserPosts(userId);
		if (userPosts.size() > 0) {
			// make Get/posts to get all the posts for userId sent.
			testContext.getCommentsManager().setUserPosts(userPosts);
		} else {
			logger.info("Posts not found for given userId...");
			Assert.fail();
		}
	}

	// searchCommentsOnPosts method will request - Get/comments to get comments
	// based on postId sent
	protected void searchCommentsOnPosts() {
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getListOfIntegers();
		List<Integer> userPosts = testContext.getCommentsManager().getUserPosts();

		if (userPosts.size() > 0) {
			// make Get/comments to get all the comments made on each postId
			// sent
			userPosts.forEach(postId -> {
				commentIdsOnPosts.addAll(testContext.getEndPoints().getPostCommentIds(postId));
			});
		} else {
			logger.info("Posts not found for given userId...");
			Assert.fail();
		}
		testContext.getCommentsManager().setCommentIdsOnPosts(commentIdsOnPosts);
	}

	// getEmailIdsForComments method will request - Get/comments to get emailId
	// for each commentId sent
	protected void getEmailIdsForComments() {
		List<String> emailIdsFromComments = testContext.getCommentsManager().getListOfStrings();
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getCommentIdsOnPosts();

		// make Get/comments to get all the emails comments made on user posts
		// for each commentId sent
		commentIdsOnPosts.forEach(commentId -> {
			emailIdsFromComments.addAll(testContext.getEndPoints().getEmailIdsFromComments(commentId));
		});
		testContext.getCommentsManager().setEmailIdsFromComments(emailIdsFromComments);
	}

	// getNamesForComments method will request - Get/comments to get name for
	// each commentId sent
	protected void getNamesForComments() {
		List<String> namesFromComments = testContext.getCommentsManager().getListOfStrings();
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getCommentIdsOnPosts();

		// make Get/comments to get all the names on comments made on user posts
		// for each commentId sent
		commentIdsOnPosts.forEach(commentId -> {
			namesFromComments.addAll(testContext.getEndPoints().getNamesFromComments(commentId));
		});
		testContext.getCommentsManager().setNamesFromComments(namesFromComments);
	}

	// getBodysForComments method will request - Get/comments to get body for
	// each commentId sent
	protected void getBodysForComments() {
		List<Integer> commentIdsOnPosts = testContext.getCommentsManager().getCommentIdsOnPosts();
		List<String> bodysFromComments = testContext.getCommentsManager().getListOfStrings();

		// make Get/comments to get all the names on comments made on user posts
		// for each commentId sent
		commentIdsOnPosts.forEach(commentId -> {
			bodysFromComments.addAll(testContext.getEndPoints().getBodysFromComments(commentId));
		});
		testContext.getCommentsManager().setBodysFromComments(bodysFromComments);
	}

	// validateFormatOfEmailIds will validate the format of each email address
	// stored in list
	protected void validateFormatOfEmailIds() {
		List<Integer> invalidEmailIds = testContext.getCommentsManager().getListOfIntegers();
		List<String> emailIdsFromComments = testContext.getCommentsManager().getEmailIdsFromComments();
		emailIdsFromComments.forEach(emailId -> {
			if (!EmailValidation.isValidEmail(emailId)) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts()
						.get(emailIdsFromComments.indexOf(emailId));
				invalidEmailIds.add(commentId);
			}
		});

		if (invalidEmailIds.size() > 0) {
			logger.info("Below list of commentIds where email addresses are in invalid format..");
			invalidEmailIds.forEach(id -> {
				logger.info(id.toString());
			});
		}
	}

	// validateIfFieldEmpty will validate if the field given is empty and
	// publish comment id
	protected void validateIfFieldEmpty(List<String> textToValidate, String text) {
		List<Integer> blankText = testContext.getCommentsManager().getListOfIntegers();
		textToValidate.forEach(textValue -> {
			if (textValue.isEmpty()) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts()
						.get(textToValidate.indexOf(textValue));
				blankText.add(commentId);
			}
		});

		if (blankText.size() > 0) {
			logger.info("Below list of commentIds where empty " + text + " values are prvided...");
			blankText.forEach(id -> {
				logger.info(id.toString());
			});
		}
	}

	// validateLengthOfCommentField will check if the field length is less than
	// given size
	protected void validateLengthOfCommentField(List<String> textToValidate, int commentLength, String nameOfField) {
		List<Integer> blankText = testContext.getCommentsManager().getListOfIntegers();
		textToValidate.forEach(textValue -> {
			if (textValue.length() < commentLength) {
				int commentId = testContext.getCommentsManager().getCommentIdsOnPosts()
						.get(textToValidate.indexOf(textValue));
				blankText.add(commentId);
			}
		});

		if (blankText.size() > 0) {
			logger.info("Below list of commentIds where length of " + nameOfField + " is less than " + commentLength
					+ " characters...");
			blankText.forEach(id -> {
				logger.info(id.toString());
			});
		}
	}
}
