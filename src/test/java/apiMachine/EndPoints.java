package apiMachine;

import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class EndPoints {
	private EnvironmentVariables variables;
	private String usersEndPoint;
	private String postsEndPoint;
	private String commentsEndPoint;

	public EndPoints() {
		variables = SystemEnvironmentVariables.createEnvironmentVariables();
		usersEndPoint = variables.getProperty("my.uri.users");
		postsEndPoint = variables.getProperty("my.uri.posts");
		commentsEndPoint = variables.getProperty("my.uri.comments");
	}

	// Method to return userId for give userName
	public int getUserId(String userName) {
		List<Integer> allUsersIds = SerenityRest.given().param("username", userName).get(usersEndPoint).jsonPath()
				.get("id");
		if (allUsersIds.size() == 1) {
			return allUsersIds.get(0);
		} else if (allUsersIds.size() == 0) {
			System.out.println("No user found for username: - " + userName);
			return 0;
		} else {
			System.out.println("More than one users found for username: -" + userName);
			return 0;
		}
	}

	// Method to return List of Posts for given userId
	public List<Integer> getUserPosts(int userId) {
		List<Integer> userPostIds = SerenityRest.given().param("userId", userId).get(postsEndPoint).jsonPath()
				.get("id");
		if (userPostIds.size() > 0) {
			return userPostIds;
		} else {
			System.out.println("No posts found for user with userId :- " + userId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<Integer> getPostCommentIds(int postId) {
		List<Integer> allCommentIds = SerenityRest.given().param("postId", postId).get(commentsEndPoint).jsonPath()
				.get("id");
		if (allCommentIds.size() > 0) {
			return allCommentIds;
		} else {
			System.out.println("No comments found for PostId: -" + postId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<String> getEmailIdsFromComments(int commentId) {
		List<String> emailIdsFromComments = SerenityRest.given().param("id", commentId).get(commentsEndPoint).jsonPath()
				.get("email");
		if (emailIdsFromComments.size() == 1) {
			return emailIdsFromComments;
		} else {
			System.out.println("No Email id found for comment: -" + commentId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<String> getNamesFromComments(int commentId) {
		List<String> namesFromComments = SerenityRest.given().param("id", commentId).get(commentsEndPoint).jsonPath()
				.get("name");
		if (namesFromComments.size() == 1) {
			return namesFromComments;
		} else {
			System.out.println("No names found for the for comment: -" + commentId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<String> getBodysFromComments(int commentId) {
		List<String> bodysFromComments = SerenityRest.given().param("id", commentId).get(commentsEndPoint).jsonPath()
				.get("body");
		if (bodysFromComments.size() == 1) {
			return bodysFromComments;
		} else {
			System.out.println("No text body found for comment: -" + commentId);
			return null;
		}
	}

}
