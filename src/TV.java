


public class TV extends Product{

	private int year;
	private String genre;
	private String director;
	private int episodes;
	private String star;
	
	public TV(String name, float price, int quantity, ProductType type) {
		super(name, price, quantity, type);
	}
	
	public TV(ProductType type, String name, float price, int year, String genre, String director, int quantity, int episodes, String star){
		super(name, price, quantity, type);
		this.year = year;
		this.genre = genre;
		this.director = director;
		this.episodes = episodes;
		this.star = star;
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

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
	
	@Override
	public String toString(){
		//-Title,year,genre,price,director,quantity,episodes,star
		return this.getName() + FileHandler.SPLIT_COMMA + 
				this.year + FileHandler.SPLIT_COMMA + 
				this.genre +FileHandler.SPLIT_COMMA + 
				this.getPrice()	+ FileHandler.SPLIT_COMMA + 
				this.director + FileHandler.SPLIT_COMMA + 
				this.getQuantity() + FileHandler.SPLIT_COMMA +
				this.episodes + FileHandler.SPLIT_COMMA +
				this.star;
	}
	
}
