package objects;

import java.util.ArrayList;

/**
 * A Review for a particular coffee shop written by a user.
 * 
 * @author Calvin
 * @version 17.02.13
 */
public class Review {
	
        private int reviewId;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
        
	/* The user who submitted the review */
	private int myUserId;
        
        /* The ID of the coffee shop that this review is for. */
        private int myShopId;
	
	/* The coffee score */
	private int myCoffeeScore;
	
	/* The burrito score */
	private int myBurritoScore;

    public void setMyUserId(int myUserId) {
        this.myUserId = myUserId;
    }

    public void setMyShopId(int myShopId) {
        this.myShopId = myShopId;
    }

    public void setMyCoffeeScore(int myCoffeeScore) {
        this.myCoffeeScore = myCoffeeScore;
    }

    public void setMyBurritoScore(int myBurritoScore) {
        this.myBurritoScore = myBurritoScore;
    }

    public void setMyDollarScore(int myDollarScore) {
        this.myDollarScore = myDollarScore;
    }

    public void setMyReview(String myReview) {
        this.myReview = myReview;
    }

    public void setMyHelpful(int myHelpful) {
        this.myHelpful = myHelpful;
    }

    /* The Dollar score */
    public void setMyUnhelpful(int myUnhelpful) {
        this.myUnhelpful = myUnhelpful;
    }

	private int myDollarScore;

	/* The review text. */
	private String myReview;
	
	/* The number of users who think the review was helpful */
	private int myHelpful;
	
	/* The number of users who think the review was unhelpful */
	private int myUnhelpful;
	
	/**
	 * Creates a new review for a given coffee shop.
	 * @param theUser The user who posts the interview
	 * @param theCoffee The coffee score given by the user
	 * @param theBurrito The burrito score given by the user
	 * @param theDollar The dollar score given by the user
	 * @param theReview The contents of the review.
	 */
	public Review(int theUserId, int theShopId, int theCoffee, int theBurrito, int theDollar, String theReview) {
		myUserId = theUserId;
                myShopId = theShopId;
		myCoffeeScore = theCoffee;
		myBurritoScore = theBurrito;
		myDollarScore = theDollar;
		myReview = theReview;
	}
	
        public Review() {
        }
        
	/**
	 * Retrieves the user ID of the review's author.
	 * @return the user ID of the reviewer
	 */
	public int getUserId() {
		return myUserId;
	}
        
        /**
	 * Retrieves the shop ID that the user is reviewing.
	 * @return the shop ID of the shop being reviewed.
	 */
	public int getUser() {
		return myUserId;
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
		return myHelpful;
	}
	
	/**
	 * Gets the number of unhelpful ratings
	 * @return number of unhelpful ratings
	 */
	public int getNumUnhelpful() {
		return myUnhelpful;
	}
        
        public int getShopId() {
            return myShopId;
        }
}
