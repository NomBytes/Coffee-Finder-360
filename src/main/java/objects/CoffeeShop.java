



/**
 * A Coffee Shop page users can rate or look at reviews.
 * 
 * @author Calvin
 * @version 17.02.13
 */

package objects;

public class CoffeeShop {

	/* The name of the shop */
	private String myShopName;
	
	/* The address of the shop */
	private String myAddress;
        
        private String myPhone;
	
        private int myShopId;

	
        
        public CoffeeShop() {
            myShopId = 1;
        }
        
	/**
	 * Constructs a new coffee shop given the name and address.
	 * @param theName The name of the coffee shop
	 * @param theAddress The location of the coffee shop
         * @param thePhone The shop phone number.
	 */
	public CoffeeShop(String theName, String theAddress, String thePhone) {
		myShopName = theName;
		myAddress = theAddress;
                myPhone = thePhone;
                myShopId = 1;

	}

	
        public int getShopId() {
            return this.myShopId;
        }

        public void setShopId(int theShopId) {
            this.myShopId = theShopId;
        }
 
	
	public String getShopName() {
		return this.myShopName;
	}
        
        public void setShopName(String theShopName) {
            this.myShopName = theShopName;
        }
	
	public String getAddress() {
		return myAddress;
	}
        
        public void setAddress(String theAddress) {
            this.myAddress = theAddress;
        }
	
        public String getPhone() {
            return myPhone;
        }
        
        public void setPhone(String thePhone) {
            this.myPhone = thePhone;
        }

}