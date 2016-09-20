package source;

import beans.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.Cart;
import beans.CartItem;
import beans.Customer;
import beans.Game;
import beans.ProductType;
import io.FileHandler;

public class DummyModel implements Model
{

    ArrayList<Product> productList = new ArrayList<Product>();
//    HashMap<String, String> passwords = new HashMap<>();
    HashMap<String, Customer> customerList = new HashMap<>();

    public DummyModel()
    {
        loadData();
//        customerList.put("admin", new Customer("john", "900 dandenong rd", "123", "456"));

//        for (int i = 0; i < 3; i++)
//        {
//            Product birb = new Product("Item #" + i) {};
//            birb.setProperty("price", "Price ($)", 100f);
//            productList.add(birb);
//        }
    }

    private void loadData()
    {
        // load customer data
        loadCusteomerData();
        // load product data
        loadProductData();
    }

    private void loadGameData()
    {
        String gameData = FileHandler.readFromFile(FileHandler.GAME_DATA);
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

    private void loadProductData()
    {
        loadGameData();
    }

    private void loadCusteomerData()
    {
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
            System.out.println(c.getUsername());
        }
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
//        if (passwords.containsKey(username))
//        {
//            return false;
//        }
//        passwords.put(username, password);
//        customerList.put(username, new Customer(username, "", "", ""));
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
    	// TODO
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
    	// order file data format
    	// currentUserID, product
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
   		FileHandler.writeToFile(gameData,FileHandler.GAME_DATA);
   		// TODO: not implement other products now
//   		FileHandler.writeToFile(movieData,FileHandler.MOVIE_DATA);
//   		FileHandler.writeToFile(TVData,FileHandler.TV_DATA);
//   		FileHandler.writeToFile(musicData,FileHandler.MUSIC_DATA);
   	}

}
