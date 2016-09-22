/*
 * Customer object
 */



/**
 * <pre>
 * This class represents a customer, it holds all of the data that the store needs to know about someone.
 * Whilst this information is not sufficient to place an order in real life, it is detailed enough to serve as a good learning example.
 * </pre>
 */
public class Customer {

	// customer id
	private int id;
	// customer real name
	private String name;
	// user name in system
	private String username;
	// customer password
	private String password;
	// security question
	private String secrQues;
	// security question answer
	private String answer;
	// date of birth
	private String dob;
	// customer address
	private String address;
	// card number
	private String cardNumber;
	// customer's phone number
	private String phone;
	
	private Cart cart;

	// Customer account constructor
	public Customer(String name, String address, String cardNumber, String phone){
		this.name = name;
		this.address = address;
		this.cardNumber = cardNumber;
		this.phone = phone;
	}
	
	// Customer constructor
	public Customer(int id, String name, String username, String password,
			String dob) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.dob = dob;
		// current file does not contain following informatin
		this.address = "";
		this.phone = "";
		this.cardNumber = "";
		if(cart == null){
			this.cart = new Cart();	
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSecrQues() {
		return secrQues;
	}

	public String getAnswer() {
		return answer;
	}

	public String getDob() {
		return dob;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecrQues(String secrQues) {
		this.secrQues = secrQues;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
