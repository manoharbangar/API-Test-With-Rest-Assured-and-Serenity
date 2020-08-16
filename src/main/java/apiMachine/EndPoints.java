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
		Assert.assertEquals(response.getStatusCode(), 200);
		List<Integer> allUsersIds = response.jsonPath().get("id");
		if (allUsersIds.size() == 1) {
			return allUsersIds.get(0);
		} else {
			return -1;
		}
	}

	// Method to return List of Post ids for given userId
	public List<Integer> getUserPosts(int userId) {
		Response response = SerenityRest.given().param("userId", userId).get(postsEndPoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		List<Integer> userPostIds = response.jsonPath().get("id");
			return userPostIds;
	}

	// Method to return List of comment ids for given post id
	public List<Integer> getPostCommentIds(int postId) {
		Response response = SerenityRest.given().param("postId", postId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		List<Integer> allCommentIds = response.jsonPath().get("id");
			return allCommentIds;
	}

	// Method to return list of email ids for given commentId
	public List<String> getEmailIdsFromComments(int commentId) {
		Response response = SerenityRest.given().param("id", commentId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		List<String> emailIdsFromComments = response.jsonPath().get("email");
			return emailIdsFromComments;
	}

	// Method to return List of Names for given commentId
	public List<String> getNamesFromComments(int commentId) {
		Response response = SerenityRest.given().param("id", commentId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		List<String> namesFromComments = response.jsonPath().get("name");
			return namesFromComments;
	}

	// Method to return List of comment body for given commentId
	public List<String> getBodysFromComments(int commentId) {
		Response response = SerenityRest.given().param("id", commentId).get(commentsEndPoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		List<String> bodysFromComments = response.jsonPath().get("body");
			return bodysFromComments;
	}
}
