

/**
 * This class represents a TV series product entity,
 */
public class TV extends Product{

	private String genre;
	private String director;
	private int episodes;
	private String star;
	
	public TV(String name, float price, int quantity, ProductType type, int year) {
		super(name, price, quantity, type, year);
	}
	
	public TV(ProductType type, String name, float price, int year, String genre, String director, int quantity, int episodes, String star){
		super(name, price, quantity, type, year);
		this.year = year;
		this.genre = genre;
		this.director = director;
		this.episodes = episodes;
		this.star = star;
		//Add and show extra property (by Richard)
				this.setProperty("year", "Year", year);
				this.setProperty("genre", "Genre", genre);
				this.setProperty("director", "Director", director);
				this.setProperty("episodes", "Episodes", episodes);
				this.setProperty("star", "Star", star);
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
