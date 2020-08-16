package apiMachine;

import java.util.List;

import org.junit.Assert;

import io.restassured.response.Response;
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
		Response response = SerenityRest.given().param("username", userName).get(usersEndPoint);
		Assert.assertEquals(response.getStatusCode(),200);
		List<Integer> allUsersIds = response.jsonPath().get("id");
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
		System.out.println(userId);
		Response response = SerenityRest.given().param("userId", userId).get(postsEndPoint);
		Assert.assertEquals(response.getStatusCode(),200);
		List<Integer> userPostIds = response.jsonPath().get("id");
		if (userPostIds.size() > 0) {
			return userPostIds;
		} else {
			System.out.println("No posts found for user with userId :- " + userId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<Integer> getPostCommentIds(int postId) {
		Response response = SerenityRest.given().param("postId", postId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(),200);
		List<Integer> allCommentIds = response.jsonPath().get("id");
		if (allCommentIds.size() > 0) {
			return allCommentIds;
		} else {
			System.out.println("No comments found for PostId: -" + postId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<String> getEmailIdsFromComments(int commentId) {
		Response response = SerenityRest.given().param("id", commentId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(),200);
		List<String> emailIdsFromComments = response.jsonPath().get("email");
		if (emailIdsFromComments.size() == 1) {
			return emailIdsFromComments;
		} else {
			System.out.println("No Email id found for comment: -" + commentId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<String> getNamesFromComments(int commentId) {
		Response response = SerenityRest.given().param("id", commentId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(),200);
		List<String> namesFromComments = response.jsonPath().get("name");
		if (namesFromComments.size() == 1) {
			return namesFromComments;
		} else {
			System.out.println("No names found for the for comment: -" + commentId);
			return null;
		}
	}

	// Method to return List of Posts for given userId
	public List<String> getBodysFromComments(int commentId) {
		Response response = SerenityRest.given().param("id", commentId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(),200);
		List<String> bodysFromComments = response.jsonPath().get("body");
		if (bodysFromComments.size() == 1) {
			return bodysFromComments;
		} else {
			System.out.println("No text body found for comment: -" + commentId);
			return null;
		}
	}

}
