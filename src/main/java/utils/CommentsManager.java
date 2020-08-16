package utils;

import java.util.ArrayList;
import java.util.List;

public class CommentsManager {
	private int userId;
	private List<Integer> userPosts;
	private List<Integer> commentIdsOnPosts;
	private List<String> emailIdsFromComments;
	private List<String> namesFromComments;
	private List<String> bodysFromComments;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Integer> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<Integer> userPosts) {
		this.userPosts = userPosts;
	}

	public List<Integer> getCommentIdsOnPosts() {
		return commentIdsOnPosts;
	}

	public void setCommentIdsOnPosts(List<Integer> commentIdsOnPosts) {
		this.commentIdsOnPosts = commentIdsOnPosts;
	}

	public List<String> getEmailIdsFromComments() {
		return emailIdsFromComments;
	}

	public void setEmailIdsFromComments(List<String> emailIdsFromComments) {
		this.emailIdsFromComments = emailIdsFromComments;
	}

	public List<String> getNamesFromComments() {
		return namesFromComments;
	}

	public void setNamesFromComments(List<String> namesFromComments) {
		this.namesFromComments = namesFromComments;
	}

	public List<String> getBodysFromComments() {
		return bodysFromComments;
	}

	public void setBodysFromComments(List<String> bodysFromComments) {
		this.bodysFromComments = bodysFromComments;
	}
	
	public ArrayList<String> getListOfStrings(){
		return new ArrayList<String>();
	}
	
	public ArrayList<Integer> getListOfIntegers(){
		return new ArrayList<Integer>();
	}
	
	

}
