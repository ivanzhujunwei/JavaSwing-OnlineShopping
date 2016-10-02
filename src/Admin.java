/**
 * 
 * This class represents a admin entity.Admin will have different access and views from customers
 */
public class Admin extends User{

	public Admin(int id, String name, String username, String password,
			String dob) {
		super(id, name, username, password, dob);
	}
}
