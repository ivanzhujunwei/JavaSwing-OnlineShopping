package source;

import beans.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import beans.Cart;
import beans.CartItem;
import beans.Customer;
import beans.Game;
import beans.Movie;
import beans.ProductType;
import beans.TV;
import io.FileHandler;

public class DummyModel implements Model
{
	// product name must not the same!!!
    ArrayList<Product> productList = new ArrayList<Product>();
    HashMap<String, String> passwords = new HashMap<>();
    HashMap<String, Customer> customerList = new HashMap<>();

    public DummyModel()
    {
        loadData();
    }

    private void loadData()
    {
    	// load product data
        loadProductData();
        // load customer data
        loadCusteomerData();
        
    }

    private void loadGameData()
    {
        String gameData = FileHandler.readFromFile(FileHandler.GAME_FILE);
        String[] gameArray = gameData.split(FileHandler.SPLIT_CEMI);
        for (int i = 0; i < gameArray.length; i++)
        {
            String[] game = gameArray[i].split(FileHandler.SPLIT_COMMA);
            String name = game[0];
            int issueYear = Integer.parseInt(game[1]);
            String platform = game[2];
            String price = game[3];
            String publisher = game[4];
            String quantity = game[5];
            Game c = new Game(ProductType.GAME, name, issueYear, platform, Float.parseFloat(price), publisher, Integer.parseInt(quantity));
            productList.add(c);
        }
    }

    /***
     * Load movie data from file
     * File format: name,year,genre,price,director,quantity,numberOfFilms
     */
    private void loadMovieData(){
    	 String movieData = FileHandler.readFromFile(FileHandler.MOVIE_FILE);
         String[] movieArray = movieData.split(FileHandler.SPLIT_CEMI);
         for (int i = 0; i < movieArray.length; i++)
         {
             String[] movie = movieArray[i].split(FileHandler.SPLIT_COMMA);
             String name = movie[0];
             int year = Integer.parseInt(movie[1]);
             String genre = movie[2];
             float price = Float.parseFloat(movie[3]);
             String director = movie[4];
             int quantity = Integer.parseInt(movie[5]);
             int numberOfFilms = Integer.parseInt(movie[6]);
             Movie c = new Movie(ProductType.MOVIE, name, price, year, genre, director, quantity, numberOfFilms);
             productList.add(c);
         }
    }
    
    /***
     * load TV data
     */
    private void loadTVData(){
    	String TVData = FileHandler.readFromFile(FileHandler.TV_FILE);
        String[] TVArray = TVData.split(FileHandler.SPLIT_CEMI);
        for (int i = 0; i < TVArray.length; i++)
        {
            String[] tv = TVArray[i].split(FileHandler.SPLIT_COMMA);
            String name = tv[0];
            int year = Integer.parseInt(tv[1]);
            String genre = tv[2];
            float price = Float.parseFloat(tv[3]);
            String director = tv[4];
            int quantity = Integer.parseInt(tv[5]);
            int episodes = Integer.parseInt(tv[6]);
            String star = tv[7];
            //ProductType type, String name, float price, int year, String genre, String director, int quantity, int episodes, String star
            TV tvObj = new TV(ProductType.TV, name, price, year, genre, director, quantity, episodes, star);
            productList.add(tvObj);
        }
    }
    
    /***
     * load product data
     */
    private void loadProductData()
    {
        loadGameData();
        loadMovieData();
        loadTVData();
    }

    /***
     * Load customer data
     */
    private void loadCusteomerData()
    {
        loadCustomerDetail();
        loadCustomerCart();
    }

    /***
     * Load customer personal information
     */
    private void loadCustomerDetail(){
    	String customerData = FileHandler.readFromFile(FileHandler.CUSTOMER_DATA);
        String[] customerArray = customerData.split(FileHandler.SPLIT_CEMI);
        for (int i = 0; i < customerArray.length; i++)
        {
            String[] customers = customerArray[i].split(FileHandler.SPLIT_COMMA);
            String idStr = customers[0].trim();
            int id = Integer.parseInt(idStr.trim());
            String name = customers[1];
            String username = customers[2];
            String password = customers[3];
            String securityQuestion = customers[4];
            String securityAnswer = customers[5];
            String dob = customers[6];
            Customer c = new Customer(id, name, username, password, dob);
            c.setSecrQues(securityQuestion);
            c.setAnswer(securityAnswer);
            customerList.put(username, c);
            passwords.put(username, password);
        }
    }
    
    /***
     * Load customer cart information
     */
    private void loadCustomerCart(){
    	String cartData = FileHandler.readFromFile(FileHandler.CART_FILE);
    	 String[] cartArray = cartData.split(FileHandler.SPLIT_CEMI);
         for (int i = 0; i < cartArray.length; i++)
         {
             String[] cartOfCustomer = cartArray[i].split(FileHandler.SPLIT_COMMA);
             String userId = cartOfCustomer[0];
             Cart cart = customerList.get(userId).getCart();
             for (int j = 1;j<cartOfCustomer.length; j = j+2){
            	 Product p = getProductByName(cartOfCustomer[j]);
            	 int productQuantity = Integer.parseInt(cartOfCustomer[j+1]);
            	 CartItem ct = new CartItem(p,productQuantity);
            	 cart.add(ct);
             }
         }
    }
    
    /***
     * Return a product by its name
     * @param name product name
     * @return product
     */
    private Product getProductByName (String name){
    	for(Product p : productList){
    		if (p.getName().equals(name)){
    			return p;
    		}
    	}
    	return null;
    }

    @Override
    public List<Product> getProducts()
    {
        return productList;
    }
    
    

    @Override
    public boolean login(String username, String password)
    {
        Customer loginUser = customerList.get(username);
        if (loginUser != null && password.equals(loginUser.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public boolean signup(String username, String password)
    {
        if (passwords.containsKey(username))
        {
            return false;
        }
        passwords.put(username, password);
        customerList.put(username, new Customer(username, "", "", ""));
        return true;
    }

    @Override
    public Customer getUserInfo(String username)
    {
        return customerList.get(username);
    }

    @Override
    public boolean setUserInfo(String username, Customer info)
    {
        customerList.put(username, info);
        return true;
    }

    @Override
    public float getPrice(Cart cart)
    {
        float total = 0;
        for (CartItem item : cart.getList())
        {
            if (item.product.hasProperty("price"))
            {
                total += ((float) item.product.getPropertyValue("price")) * item.quantity;
            }
        }
        return total;
    }

    @Override
	public boolean processOrder(String currentUserID, Cart cart)
    {
    	// Check if the quantity is enough
    	for (CartItem ct: cart.getList()){
    		// if product quantity is not enough, not processing
    		int productQuantity = ct.product.getQuantity();
    		if (productQuantity < ct.quantity){
    			return false;
    		}
    		// Update the quantity of the product
    		int currentProductQuantity = (int) (productQuantity - ct.quantity);
    		ct.product.setQuantity(currentProductQuantity);
    	}
    	// save product
    	saveProductData();
    	// save order
    	saveOrderData(currentUserID, cart);
        return true;
    }
    
    /***
     * Save order data into file
     */
    public void saveOrderData(String currentUserID, Cart cart){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	// order file data format
    	// currentUserID^time^productName^^productQuantity^^^productName^^productQuantity^^^
    	String orderData = currentUserID + FileHandler.SPLIT_COMMA + dateFormat.format(date) + FileHandler.SPLIT_COMMA;
    	for(CartItem ct: cart.getList()){
    		// different product and its quantity is separated by "^^^"
    		orderData += ct.toString() + FileHandler.SPLIT_COMMA;
    	}
    	orderData += "\n";
    	FileHandler.appendToFile(orderData, FileHandler.ORDER_FILE);
    }
    
    /*
     * Save product data into file
     */
    public void saveProductData()
   	{
    	// initialize different data which needs to be stored in file
    	String gameData = "";
    	String movieData = "";
    	String TVData = "";
    	String musicData = "";
   		for (Product p :productList)
   		{
   			switch(p.getType()){
   			case GAME:
   				gameData += p.toString() + "\n";
   				break;
   			case MUSIC:
   				musicData += p.toString() + "\n";
   				break;
   			case MOVIE:
   				movieData += p.toString() + "\n";
   				break;
   			case TV:
   				TVData += p.toString() + "\n";
			default:
				break;
   			}
   		}
   		FileHandler.writeToFile(gameData,FileHandler.GAME_FILE);
   		FileHandler.writeToFile(movieData,FileHandler.MOVIE_FILE);
   		FileHandler.writeToFile(TVData,FileHandler.TV_FILE);
   		// TODO: comment now because MUSIC file has not been read yet
//   		FileHandler.writeToFile(musicData,FileHandler.MUSIC_FILE);
   	}

	@Override
	public HashMap<String, Customer> getCustomerList() {
		return customerList;
	}

}
