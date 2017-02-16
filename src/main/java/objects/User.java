/**
 * 
 */

/**
 * @author nombi
 *
 */
package objects;

public class User {
        /** The Username. **/
	private String username;
        
        /** The Email. **/
	private String email;
        
        /** The Password. */
	private String password;
        
        /** The User ID. */
	private int userId;
	
	public void user(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
		userId = createId();
	}
	
	public int createId(){
		return 1;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
}
