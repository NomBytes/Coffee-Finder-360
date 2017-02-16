



/**
 * A Coffee Shop page users can rate or look at reviews.
 * 
 * @author Calvin
 * @version 17.02.13
 */

package objects;

public class CoffeeShop {

	/* The name of the shop */
	private String myshopname;
	
	/* The address of the shop */
	private String myaddress;
        
        private String myphone;
	
        private int myshopId;

	
        
        public CoffeeShop() {
            myshopId = 1;
        }
        
	/**
	 * Constructs a new coffee shop given the name and address.
	 * @param theName The name of the coffee shop
	 * @param theAddress The location of the coffee shop
         * @param thePhone The shop phone number.
	 */
	public CoffeeShop(String myshopname, String myaddress, String myphone) {
		this.myshopname = myshopname;
		this.myaddress = myaddress;
                this.myphone = myphone;
                myshopId = 1;

	}

	
        public int getShopId() {
            return myshopId;
        }

        public void setShopId(int theShopId) {
            this.myshopId = theShopId;
        }
 
	
	public String getShopName() {
		return this.myshopname;
	}
        
        public void setShopName(String theShopName) {
            this.myshopname = theShopName;
        }
	
	public String getAddress() {
		return myaddress;
	}
        
        public void setAddress(String theAddress) {
            this.myaddress = theAddress;
        }
	
        public String getPhone() {
            return myphone;
        }
        
        public void setPhone(String thePhone) {
            this.myphone = thePhone;
        }

}