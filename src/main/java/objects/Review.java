package tcss360;

import java.util.ArrayList;

/**
 * A Review for a particular coffee shop written by a user.
 * 
 * @author Calvin
 * @version 17.02.13
 */
public class Review {
	
	/* The user who submitted the review */
	private String myUsername;
	
	/* The coffee score */
	private int myCoffeeScore;
	
	/* The burrito score */
	private int myBurritoScore;
	
	/* The Dollar score */
	private int myDollarScore;
	
	/* The Tags used in the review */
	private ArrayList<String> myTags;
	
	/* The comments within the review */
	private String myReview;
	
	/* The users who think the review was helpful */
	private ArrayList<String> myHelpful;
	
	/* The users who think the review was unhelpful */
	private ArrayList<String> myUnhelpful;
	
	/**
	 * Creates a new review for a given coffee shop.
	 * @param theUser The user who posts the interview
	 * @param theCoffee The coffee score given by the user
	 * @param theBurrito The burrito score given by the user
	 * @param theDollar The dollar score given by the user
	 * @param theTags The tags given by the user.
	 * @param theReview The contents of the review.
	 */
	public Review(String theUser, int theCoffee, int theBurrito, int theDollar, 
			      ArrayList<String> theTags, String theReview) {
		myUsername = theUser;
		myCoffeeScore = theCoffee;
		myBurritoScore = theBurrito;
		myDollarScore = theDollar;
		myTags = theTags;  //NEED TO CONSIDER FIXING
		myReview = theReview;
	}
	
	/**
	 * Retrieves the username of the review's author.
	 * @return the username of the reviewer
	 */
	public String getUser() {
		return myUsername;
	}
	
	/**
	 * Returns the given coffee score from the user
	 * @return The reviewer's coffee score.
	 */
	public int getCoffeeScore() {
		return myCoffeeScore;
	}
	
	/**
	 * Returns the given burrito score from the user
	 * @return The reviewer's coffee score.
	 */
	public int getBurritoScore() {
		return myBurritoScore;
	}
	
	/**
	 * Returns the given dollar score from the user
	 * @return The reviewer's dollar score.
	 */
	public int getDollarScore() {
		return myDollarScore;
	}
	
	/**
	 * Returns the associated tags from the review
	 * @return The tags associated from the review
	 */
	public ArrayList<String> getTags() {
		return myTags; //CONSIDER FIXING
	}
	
	/**
	 * Grabs a specific tag from the user interview
	 * @param theElement The location of the tag
	 * @return The tag at the specific location
	 */
	public String getTagAt(int theElement) {
		return myTags.get(theElement);
	}
	
	/**
	 * Gets the number of tags from the review
	 * @return the number of tags from the review
	 */
	public int getNumberOfTags() {
		return myTags.size();
	}

	/**
	 * Gets the contents of the review
	 * @return The review text
	 */
	public String getReview() {
		return myReview;
	}
	
	/**
	 * Gets the number of helpful ratings
	 * @return number of helpful ratings
	 */
	public int getNumHelpful() {
		return myHelpful.size();
	}
	
	/**
	 * Gets the number of unhelpful ratings
	 * @return number of unhelpful ratings
	 */
	public int getNumUnhelpful() {
		return myUnhelpful.size();
	}
}
