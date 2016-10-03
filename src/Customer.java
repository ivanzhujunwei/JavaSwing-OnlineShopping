/*
 * Customer object
 */



/**
 * <pre>
 * This class represents a customer, it holds all of the data that the store needs to know about someone.
 * Whilst this information is not sufficient to place an order in real life, it is detailed enough to serve as a good learning example.
 * </pre>
 * Customer is the main user of this shopping system. Customer is able to view all the products, save products into their own cart and place orders.
 */
public class Customer extends User {

	
	private Cart cart;

	// Customer account constructor
	public Customer(String name, String address, String cardNumber, String phone){
		super(name,address,cardNumber,phone);
	}
	
	// Customer constructor
	public Customer(int id, String name, String username, String password,
			String dob) {
		super(id,name,username,password,dob);
		if(cart == null){
			this.cart = new Cart();	
		}
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
