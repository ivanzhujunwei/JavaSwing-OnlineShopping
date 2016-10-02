


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * This class represents a game product entity,
 */
public class Game extends Product{

	private String platform;
    private String publisher;
            
    public Game(ProductType type, String name,int year,String platform, double price,String publisher,int quantity)
    {
        super(name,price,quantity,type,year);
        this.platform = platform;
//        this.issueYear  = issueYear;
        this.publisher = publisher;
//        this.quantity = quantity;
      //Add and show extra property (by Richard)
  		this.setProperty("issueYear", "IssueYear", year);
  		this.setProperty("platform", "Platform", platform);
  		this.setProperty("publisher", "Publisher", publisher);
    }


    public String getPlatform()
    {
        return platform;
    }

    public String getPublisher()
    {
        return publisher;
    }



    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    @Override
	public String toString() {
		return this.getName() + FileHandler.SPLIT_COMMA + 
				super.year + FileHandler.SPLIT_COMMA + 
				this.platform +FileHandler.SPLIT_COMMA + 
				this.getPrice()	+ FileHandler.SPLIT_COMMA + 
				publisher + FileHandler.SPLIT_COMMA + 
				this.getQuantity();
	}
    
}
