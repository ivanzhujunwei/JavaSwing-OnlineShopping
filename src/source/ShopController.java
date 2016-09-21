package source;

import gui.LoginView;
import beans.Cart;
import beans.CartItem;
import beans.Product;
import gui.CartView;
import gui.ConfirmDialog;
import gui.ProductListView;
import gui.View;
import beans.Customer;
import io.FileHandler;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShopController {

	/*
	 * STATIC FIELDS
	 */
	
	/**
	 * <pre>
	 * The default product icon.
	 * </pre>
	 */
	public static ImageIcon NO_IMAGE_ICON = generateIcon("https://placeholdit.imgix.net/~text?txtsize=23&bg=ffffff&txtclr=000000&txt=No+Image&w=200&h=200", 150, 150);
	/**
	 * <pre>
	 * The store logo.
	 * </pre>
	 */
	public static ImageIcon LOGO_ICON = new ImageIcon(ShopController.class.getResource("logo.png"));
	/**
	 * <pre>
	 * The image cache that is used to save time and speed up loading.
	 * </pre>
	 */
	public static HashMap<String, ImageIcon> IMAGE_CACHE;
	
	/**
	 * <pre>
	 * Generates an icon that can be used elsewere in the application.
	 * </pre>
	 * @param imgLoc The URL of the image
	 * @param width The desired icon width
	 * @param height The desired icon height
	 * @return The generated icon
	 */
	public static ImageIcon generateIcon(String imgLoc, int width, int height){
		if(IMAGE_CACHE == null)  IMAGE_CACHE = new HashMap<String, ImageIcon>();
		if(IMAGE_CACHE.containsKey(imgLoc)) return IMAGE_CACHE.get(imgLoc);
		try {
			URL url = new URL(imgLoc);
			ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			IMAGE_CACHE.put(imgLoc, icon);
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/*
	 * END STATIC
	 */
	
	private JFrame window = new JFrame();
	private Model backend;
	
//	private Cart cart = new Cart();
	private String currentUserID;
	
	/**
	 * <pre>
	 * Creates a new instance of ShopController.
	 * Make sure to call the "init" method after this!
	 * </pre>
	 * @param b The Model with all of the back-end links that the store is to use
	 */
	public ShopController(Model b){
		this.backend = b;
	}
	
	/**
	 * <pre>
	 * Sets the store's current view after setting the view's controller and initializing it.
	 * </pre>
	 * @param view The view to set
	 */
	public void setView(View view){
		view.setController(this);
		view.initialize();
		window.setContentPane(view);
		window.revalidate();
	}
	
	/**
	 * @return The JFrame that holds the store.
	 */
	public JFrame getWindow(){
		return window;
	}
	
	/**
	 * @return The Model instance controlling the store.
	 */
	public Model getBackend(){
		return this.backend;
	}
	
	/**
	 * <pre>
	 * Initialize and show the store window.
	 * Also displays the LoginView.
	 * </pre>
	 */
	public void init(){
		window.setResizable(false);
		window.setTitle("Shop");
		window.setSize(1000, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		this.setView(new LoginView());
	}
	
	/**
	 * <pre>
	 * Shows a popup message.
	 * </pre>
	 * @param message The text in the popup window.
	 */
	public void showPopup(String message){
		JOptionPane.showMessageDialog(window, message);
	}
	
	
	/*
	 * LOGIN AND USERS
	 * ------------------------------------------------
	 */

	/**
	 * @return The customer details received from the Model instance.
	 */
	public Customer getCurrentCustomerDetails(){
		return getBackend().getUserInfo(currentUserID);
	}
	
	/**
	 * <pre>
	 * Attempts to sign up a user.
	 * 
	 * This will display an error to the user if any of the following are true:
	 * - The user ID is less than 3 chars long
	 * - The password is less than 5 chars long
	 * - The two passwords do not match.
	 * 
	 * If all of the above tests succeed then the back-end will be asked to create a user.
	 * If the back-end succeeds in creating an account, the user will be logged in, if not, they will be shown an error.
	 * </pre>
	 * @param username The entered user ID.
	 * @param pass The entered password.
	 * @param confPass The entered confirmed password.
	 */
	public void signup(String username, String pass, String confPass){
		
		// Ensuring length
		if(username.length() < 3) {
			showPopup("Your user ID must be at least 3 chars long!");
			return;
		}
		else if(pass.length() < 5){
			showPopup("Your password must be at least 5 chars long!");
			return;
		}
		else if(!pass.equals(confPass)){
			showPopup("The passwords do not match");
			return;
		}
		
		boolean success = getBackend().signup(username, pass);
		
		if(!success){
			showPopup("Signup failed, that userID may already be in use!");
		} else {
			showPopup("Your account has been created, please edit your details by clicking 'My account' in the top right.");
			attemptLogin(username, pass);
		}
	}
	
	/**
	 * <pre>
	 * Attempts to log a user into the store.
	 * 
	 * If the login succeeds, they will be presented with the product list, if not, they will be shown an error.
	 * </pre>
	 * @param username The supplied user ID
	 * @param password The supplied password
	 */
	public void attemptLogin(String username, String password){
		if(backend.login(username, password)){
			currentUserID = username;
			showProductList();
		} else {
			showPopup("Login failed! Please ensure that your user ID and password are correct.");
		}
	}
	
	/**
	 * <pre>
	 * Calls the appropriate methods on the Model instance to update the information about the current user.
	 * 
	 * If no user is logged in, an error message will be displayed in the console.
	 * </pre>
	 * @param c The new user details.
	 */
	public void updateUserDetails(Customer c){
		if(this.currentUserID != null){
			boolean success = getBackend().setUserInfo(this.currentUserID, c);
			if(!success){
				showPopup("There was an error saving your information! Please try again later.");
			}
		} else {
			System.err.println("Can't update user info, no one is signed in!");
		}
	}

	
	/*
	 * PRODUCTS
	 * ------------------------------------------------
	 */

	/**
	 * <pre>
	 * Shows the checkout dialog.
	 * </pre>
	 */
	public void showCheckout(){
		ConfirmDialog.display(this);
	}
	
	///////logout
//	public void showLogout(){
//		ConfirmDialog.display(this);
//	}
	////////////////
	
	/**
	 * @return The current user's cart.
	 */
	public Cart getCart(){
//		 return cart;
		return getCurrentCustomerDetails().getCart();
	}
	
	/**
	 * <pre>
	 * Adds a product to the cart. See "Cart.addToCart" for more information.
	 * </pre>
	 * @param p The product
	 * @param quantity The quantity to add
	 */
	public void addToCart(Product p, int quantity){
		getCurrentCustomerDetails().getCart().add(p, quantity);
//		getCurrentCustomerDetails();
	}
	
	/**
	 * <pre>
	 * Shows the cart view.
	 * </pre>
	 */
	public void showCartView(){
		setView(new CartView());
	}
	
	/**
	 * <pre>
	 * See "Model.getPrice(Cart)" for more information.
	 * </pre>
	 * @return The total price of all item in the cart
	 */
	public float getTotalCartPrice(){
		return getBackend().getPrice(getCurrentCustomerDetails().getCart());
	}

	/**
	 * <pre>
	 * Shows the product list view.
	 * </pre>
	 */
	public void showProductList() {
		setView(new ProductListView());
	}

	/**
	 * <pre>
	 * Attempts a transaction using the current user's details and the current cart.
	 * </pre>
	 */
	public void attemptTransaction() {
		Customer c = getBackend().getUserInfo(currentUserID);
		String prefix = "Order failed! ";
		if(c.getName().trim().equals("")){
			showPopup(prefix + "You have not entered your full name!");
			return;
		}
		else if(c.getAddress().trim().equals("")){
			showPopup(prefix + "You have not entered your home address!");
			return;
		}
		else if(c.getPhone().trim().equals("")){
			showPopup(prefix + "You have not entered your phone number!");
			return;
		}
		else if(c.getCardNumber().trim().equals("")){
			showPopup(prefix + "You have not entered your card number!");
			return;
		}
		
		boolean success = getBackend().processOrder(currentUserID, getCurrentCustomerDetails().getCart());
		
		if(!success){
			showPopup("Sorry, your order could not be placed! Please ensure that all of your information is correct.");
		}
		else {
			showPopup("Your order has been placed successfully! Have a nice day!");
			getCurrentCustomerDetails().getCart().clear();
			this.showCartView();
		}
	}
	
	/***
	 * Store all customers' cart data into file
	 */
	public void storeCartData(){
		String cartData = "";
		for(Entry<String, Customer> entry : getBackend().getCustomerList().entrySet()) {
		        // Retrieve each customer
		        Customer customer = entry.getValue();
		        cartData += entry.getKey();// userId
		        // Get customer's cart 
		        Cart cart = customer.getCart();
		        // Get itemList in cart
		        for (CartItem ct: cart.getList()){
		        	cartData += FileHandler.SPLIT_COMMA + ct.toString();
		        }
		        cartData += "\n";
		    }
		FileHandler.writeToFile(cartData, FileHandler.CART_FILE);
	}
	
}
