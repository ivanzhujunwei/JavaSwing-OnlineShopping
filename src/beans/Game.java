package beans;

import io.FileHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Game extends Product{
    private int issueYear;
    private String platform;
    private String publisher;
    private int quantity;
            
    public Game(ProductType type, String name,int issueYear,String platform, float price,String publisher,int quantity)
    {
        super(name,price,type);
        this.platform = platform;
        this.issueYear  = issueYear;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public int getIssueYear()
    {
        return issueYear;
    }

    public String getPlatform()
    {
        return platform;
    }

    public String getPublisher()
    {
        return publisher;
    }


    public void setIssueYear(int issueYear)
    {
        this.issueYear = issueYear;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
	public String toString() {
		return this.getName() + FileHandler.SPLIT_COMMA + 
				this.issueYear + FileHandler.SPLIT_COMMA + 
				this.platform +FileHandler.SPLIT_COMMA + 
				this.getPrice()	+ FileHandler.SPLIT_COMMA + 
				publisher + FileHandler.SPLIT_COMMA + 
				this.getQuantity();
	}
    
}
