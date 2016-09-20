package beans;

import io.FileHandler;

public class Movie extends Product{

	private int year;
	private String genre;
	private String director;
	private int numberOfFilms;
	
	public Movie(String name, float price, int quantity, ProductType type) {
		super(name, price, quantity, type);
		// TODO Auto-generated constructor stub
	}
	
	public Movie(ProductType type, String name, float price, int year, String genre, String director, int quantity, int numberOfFilms){
		super(name, price, quantity, type);
		this.year = year;
		this.genre = genre;
		this.director = director;
		this.numberOfFilms = numberOfFilms;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getNumberOfFilms() {
		return numberOfFilms;
	}

	public void setNumberOfFilms(int numberOfFilms) {
		this.numberOfFilms = numberOfFilms;
	}
	
	 @Override
		public String toString() {
		// File format: name,year,genre,price,director,quantity,numberOfFilms
			return this.getName() + FileHandler.SPLIT_COMMA + 
					this.year + FileHandler.SPLIT_COMMA + 
					this.genre +FileHandler.SPLIT_COMMA + 
					this.getPrice()	+ FileHandler.SPLIT_COMMA + 
					director + FileHandler.SPLIT_COMMA + 
					this.getQuantity();
		}

}
