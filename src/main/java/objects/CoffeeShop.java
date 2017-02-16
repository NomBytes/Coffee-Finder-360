package objects;

import java.util.ArrayList;

/**
 * A Coffee Shop page users can rate or look at reviews.
 * 
 * @author Calvin
 * @version 17.02.13
 */
public class CoffeeShop {
	
        private int myShopId;

        
	/* The name of the shop */
	private String myShopName;
	
	/* The address of the shop */
	private String myAddress;
        
        private String myPhone;
	
	/* The reviews for the shop */
	private ArrayList<Review> myReviews;
	
	/* The average coffee review score */
	private double myCoffeeAvg;
	
	/* The average burrito review score */
	private double myBurritoAvg;
	
	/* The average dollar review score */
	private double myDollarAvg;
	
	/* The tags that are associated with the shop */
	private ArrayList<String> myTags;
	
        
        public CoffeeShop() {
            
        }
	/**
	 * Constructs a new coffee shop given the name and address.
	 * @param theName The name of the coffee shop
	 * @param theAddress The location of the coffee shop
	 */
	public CoffeeShop(String theName, String theAddress) {
		myShopName = theName;
		myAddress = theAddress;
		myReviews = new ArrayList<Review>();
		myCoffeeAvg = 0.0;
		myBurritoAvg = 0.0;
		myDollarAvg = 0.0;
		myTags = new ArrayList<String>();
	}

	
        public int getShopId() {
            return myShopId;
        }

        public void setShopId(int theShopId) {
            this.myShopId = theShopId;
        }
    
	/**
	 * Retrieves the tags associated with the coffee shop.
	 * @return The tags associated with the coffee shop.
	 */
	public String getTags() {
		String result = "";
		for (int i = 0; i < myTags.size(); i++) {
			result += myTags.get(i);
			if (i < myTags.size() - 1) {
			    result += ", ";
			}
		}
		return result;
	}
	
	/**
	 * Searches the tag list to see if a specific tag is in there.
	 * @param theTag The tag to check
	 * @return Whether the tag is included in the shop.
	 */
	public boolean haveTag(String theTag) {
		for (int i = 0; i < myTags.size(); i++) {
			if (theTag.equals(myTags.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public String getShopName() {
		return myShopName;
	}
        
        public void setShopName(String theShopName) {
            myShopName = theShopName;
        }
	
	public String getAddress() {
		return myAddress;
	}
        
        public void setAddress(String theAddress) {
            myAddress = theAddress;
        }
	
        public String getPhone() {
            return myPhone;
        }
        
        public void setPhone(String thePhone) {
            myPhone = thePhone;
        }
	public double getCoffeeAvg() {
		return myCoffeeAvg;
	}
	
	public double getBurritoAvg() {
		return myBurritoAvg;
	}
	
	public double getDollarAvg() {
		return myDollarAvg;
	}
	
	/**
	 * Adds tags that haven't been associated with a shop yet.
	 * @param theReview The review to add tags from.
	 */
	private void addTags(Review theReview) {
		for (int i = 0; i < theReview.getNumberOfTags(); i++) {
			if (haveTag(theReview.getTagAt(i))) {
				continue;
			}
			myTags.add(theReview.getTagAt(i));
		}
	}
		
	/**
	 * Computes the review scores for coffee, food, and price.
	 */
	private void computeAvgs() {
		double myCoffee = 0.0;
		double myBurrito = 0.0;
		double myDollar = 0.0;
		for (int i = 0; i < myReviews.size(); i++) {
			myCoffee += (double) myReviews.get(i).getCoffeeScore();
			myBurrito += (double) myReviews.get(i).getBurritoScore();
			myDollar += (double) myReviews.get(i).getDollarScore();
		}
		myCoffeeAvg = myCoffee / (double) myReviews.size();
		myBurritoAvg = myBurrito / (double) myReviews.size();
		myDollarAvg = myDollar / (double) myReviews.size();
	}

}