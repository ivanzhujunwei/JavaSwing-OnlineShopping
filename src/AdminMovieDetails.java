import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a movie product into system.
 */
public class AdminMovieDetails extends AdminProductDetails{

	// Movie
		private JLabel genreLabel;
		private JLabel directorLabel;
		private JLabel numOfFilmLabel;
		
		private JTextField genreTxtField;
		private JTextField directorTxtField;
		private JSpinner numOfFilmsSpinner;

		public AdminMovieDetails(ShopController c,DefaultTableModel tm) {
			super(c,tm);
			setTitle("Create a Movie");
			// Create music labels and text fields
			genreLabel = new JLabel("Genre:");
			directorLabel = new JLabel("Director:");
			numOfFilmLabel = new JLabel("NO. of Films:");
			genreTxtField = new JTextField();
			directorTxtField = new JTextField();
			numOfFilmsSpinner = new JSpinner();
			numOfFilmsSpinner.setModel(new SpinnerNumberModel(1, 1, 10000, 1));


			// Add music attributes into content panel
			contentPanel.add(genreLabel);
			contentPanel.add(genreTxtField);
			contentPanel.add(directorLabel);
			contentPanel.add(directorTxtField);
			contentPanel.add(numOfFilmLabel);
			contentPanel.add(numOfFilmsSpinner);
			contentPanel.setLayout(new GridLayout(7, 2));
			
			this.getContentPane().add(contentPanel, BorderLayout.NORTH);
		}
		
		@Override
		public Product toProduct() {
			Product p = super.toProduct();
			String genre = this.getGenre().getText();
			String director = this.getDirector().getText();
			int numOfFilms = (int) this.getnumOfFilms().getValue();
			//ProductType type, String name, double price, int year, String genre, String director, int quantity, int numberOfFilms
			return new Movie(ProductType.MOVIE, p.getName(),p.getPrice(), p.getYear(), genre, director, p.getQuantity(), numOfFilms);
		}

		public JTextField getGenre() {
			return genreTxtField;
		}

		public JTextField getDirector() {
			return directorTxtField;
		}

		public JSpinner getnumOfFilms() {
			return numOfFilmsSpinner;
		}
		
		@Override
		public boolean validateInput(){
			if(super.validateInput()){
				String genreTxtField = getGenre().getText();
				String directorTxtField = getDirector().getText();
				// not null
				if(Utility.isEmpty(genreTxtField) || Utility.isEmpty(directorTxtField)){
					return false;
				}
//				// numofFilms must be digit
//				if (!Utility.isInteger(numOfFilms)){
//					return false;
//				}
				return true;
			}
			return false;
		}

}
