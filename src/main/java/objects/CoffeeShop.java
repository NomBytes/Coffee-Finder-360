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