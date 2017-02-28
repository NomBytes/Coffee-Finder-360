/**
 * A Coffee Shop page users can rate or look at reviews.
 *
 * @author Calvin
 * @version 17.02.13
 */

package objects;

public class CoffeeShop {

    public String getMyshopname() {
        return myshopname;
    }

    public void setMyshopname(String myshopname) {
        this.myshopname = myshopname;
    }
    public String getMyshopname(){
      return myshopname
    }
    public void setMyshopname(String name){
        this.myshopname = name;
    }
    public String getStreet(){
      return street;
    }
    public void setStreet(String street){
      this.myStreet = street;
    }
    public String getState(){
      return state;
    }
    public void setState( String state){
      this.myState = state;
    }
    public void setZip(int zip){
      this.myzip = zip;
    }
    public int getZip(){
      return myzip;
    }
/*    public String getMyaddress() {
        return myaddress;
    }*/

/*    public void setMyaddress(String myaddress) {
        this.myaddress = myaddress;
    }*/

    public String getMyphone() {
        return myphone;
    }

    public void setMyphone(String myphone) {
        this.myphone = myphone;
    }

    public int getMyshopId() {
        return myshopId;
    }

    /* The name of the shop */
    public void setMyshopId(int myshopId) {
        this.myshopId = myshopId;
    }

	private String myshopname;

	/* The address of the shop */
//	private String myaddress;

  private String myStreet;
  private String mystate;
  private int myzip;
  private String myphone;
  private int myshopId;
  private String mylatitude;
  private String mylongitude;

}
