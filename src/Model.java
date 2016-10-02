


import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * This class is the link between the application itself and the back-end systems that control it.
 * This class MUST be implemented and an instance must be supplied to the constructor of ShopController in order for the shop to function at all.
 * </pre>
 */
public interface Model {
	
	/**
	 * <pre>
	 * This method returns the list of all products in the store.
	 * The products in this list are used to drive all other product related actions in the store so they only need to be defined here.
	 * 
	 * The products may be loaded from a server, a web-site, or any other source, as long as they are returned from this method in a list.
	 * </pre>
	 * @return The list of products in the store.
	 */
	public List<Product> getProducts();
	
	/**
	 * <pre>
	 * This method determines if a login was successful or not.
	 * 
	 * The criteria for a successful login is usually:
	 * - The user ID is valid and the user exists
	 * - The password is a valid password
	 * - The supplied password matches with the user's stored password
	 * 
	 * ( A system like this is very insecure and should NEVER be used in the real world, this is purely for learning purposes. )
	 * </pre>
	 * @param username The user ID of the user attempting to login
	 * @param password The supplied password to be tested
	 * @param loginIdentity The user will login in as customer or admin
	 * @return This method returns true if the login was successful, or false if it was not
	 */
	public boolean login(String username, String password,String loginIdentity);
	
	/**
	 * <pre>
	 * This method takes a user ID and a password and attempts to create an account for the user.
	 * 
	 * The account creation should be unsuccessful if any of the following are true:
	 * - A user with the requested user ID already exists.
	 * - The user ID is too short.
	 * - The password is too short.
	 * - The password is too insecure (insufficient length, no special characters) OPTIONAL
	 * 
	 * If the account creation is successful, the following should take place:
	 * - The user's ID and password is stored.
	 * - A new Customer object is created and stored along with the user details.
	 * 
	 * ( A system like this is very insecure and should NEVER be used in the real world, this is purely for learning purposes. )
	 * </pre>
	 * @param username The user ID that the user would like to use
	 * @param password The password that the user would like to use
	 * @return This method returns true if the user account was successfully created, and false otherwise.
	 */
	public boolean signup(String username, String password);
	
	/**
	 * <pre>
	 * This method takes a Cart object and returns the total price of all items in that cart.
	 * 
	 * Cart's are essentially just a list that looks like the following:
	 * - CartItem (product, quantity)
	 * - CartItem (product, quantity)
	 * - CartItem (product, quantity)
	 * 
	 * As product's contain no price data by default, the price of products can be calculated using product properties.
	 * EG: A product may have a "unitPrice" property that is multiplied by the quantity for all of the CartItem's.
	 * </pre>
	 * @param cart The cart to calculate the price of
	 * @return The total price of all items in the cart.
	 */
	public double getPrice(Cart cart);
	
	/**
	 * <pre>
	 * Get a user's information.
	 * </pre>
	 * @param username The id of the user.
	 * @return A Customer object with the user's information.
	 */
	public Customer getUserInfo(String username);
	
	/**
	 * <pre>
	 * Updates the users information in the back-end.
	 * 
	 * This operation may fail due to server error or other network issues.
	 * If this happens return false, otherwise always return true.
	 * </pre>
	 * @param username The user ID of the user.
	 * @param info The information to set.
	 * @return Returns true if the operation succeeded, false otherwise.
	 */
	public boolean setUserInfo(String username, Customer info);
	
	
	/**
	 * <pre>
	 * This method processes an user's order. 
	 * 
	 * A method like this would usually send a request to the back-end server and the transaction would be processed there.
	 * Once the transaction completes the result would be sent back to this method and it would return with either true or false depending on the data from the server.
	 * 
	 * ( A system like this is very insecure and should NEVER be used in the real world, this is purely for learning purposes. )
	 * </pre>
	 * @param currentUserID The user ID of the user.
	 * @param cart The cart full of items that the user wishes to buy.
	 * @return 0 if the order was successful, return 1 if the product is not enough
	 */
	public int processOrder(String currentUserID, Cart cart);
	
	public HashMap<String, Customer> getCustomerList();
	
	public List<Product> getProductList();

	public List<AdminReport> generateReport(String productName, String start, String end);
	
}
