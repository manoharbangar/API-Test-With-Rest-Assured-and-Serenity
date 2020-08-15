package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import masterSteps.MasterSteps;

public class StepsForComments extends MasterSteps {

	@Given("userId is obtained by searching username {string}")
	public void userid_is_obtained_by_searching_username(String userName) {
		
		// searchUserName method will get userId
		searchUserName(userName);
	}

	@Given("list all the posts written by the user using userId")
	public void list_all_the_posts_written_by_the_user_using_userId() {
		// searchPosts method will get all the posts made by user
		searchPosts();
	}

	@When("list all the comments for the posts made by user")
	public void list_all_the_comments_for_the_posts_made_by_user() {
		// searchCommentsOnPosts method will search all the comments for the
		// posts made by user
		searchCommentsOnPosts();
	}

	@Then("validate that email in the comment section is in the proper format")
	public void validate_that_email_in_the_comment_section_is_in_the_proper_format() {
		// getEmailIdsForComments method will get emailIds from comments
		getEmailIdsForComments();
		// Validate emails using apache commons validator library
		validateFormatOfEmailIds();
	}

	@Then("validate that name in the comment section are not empty")
	public void validate_that_name_in_the_comment_section_are_not_empty() {
		// getNamesForComments method will get names from comments
		getNamesForComments();
		// Validate emails using apache commons validator library
		validateIfTextEmpty(testContext.getCommentsManager().getNamesFromComments());
	}

	@Then("validate that text body in the comment section is not empty")
	public void validate_that_text_body_in_the_comment_section_is_not_empty() {
		// getNamesForComments method will get bodys from comments
		getBodysForComments();
		validateIfTextEmpty(testContext.getCommentsManager().getBodysFromComments());
	}

	@Then("validate that the length of text body in the comment is not less than {string} characters")
	public void validate_that_the_length_of_text_body_in_the_comment_is_not_less_than_characters(String commentLength) {
		// getBodysForComments method will validate if length of comment body
		// is not less than required characters
		getBodysForComments();
		validateLengthOfComment(testContext.getCommentsManager().getBodysFromComments());
	}

}
