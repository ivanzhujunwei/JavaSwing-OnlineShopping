



/**
 * <pre>
 * This class represents a group of a specific product in the cart.
 * 
 * For example, "5 paint cans" would be represented in the cart as a CartItem object with the product as "paint can" and the quantity as "5".
 * This system allows the cart to know the sub-total of each item.
 * </pre>
 */
public class CartItem {

	/**
	 * <pre>
	 * The product that is represented by this CartItem
	 * </pre>
	 */
	public Product product;
	
	/**
	 * <pre>
	 * The amount of the product that this CartItem represents
	 * </pre>
	 */
	public int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	/**
	 * <pre>
	 * Adds 1 to the current quantity.
	 * </pre>
	 */
	public void add(){
		add(1);
	}
	
	/**
	 * <pre>
	 * Adds the desired amount to the quantity.
	 * </pre>
	 * @param quantity The quantity to add
	 */
	public void add(float quantity){
		this.quantity += quantity;
	}
	
	@Override
	public String toString(){
		// "^" can not be set in a product's name
		return this.product.getName() + FileHandler.SPLIT_COMMA + this.quantity;
	}
}
