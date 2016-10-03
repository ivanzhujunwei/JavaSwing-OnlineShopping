import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a TV series product into system.
 */
public class AdminTVDetails extends AdminProductDetails {

	// TV
	private JLabel genreLabel;
	private JLabel directorsLabel;
	private JLabel episodesLabel;
	private JLabel starsLabel;

	private JTextField genreTxtField;
	private JTextField directorTxtField;
	private JSpinner episodesSpinner;
	private JTextField starsTxtField;

	public AdminTVDetails(ShopController c, DefaultTableModel tm) {
		super(c, tm);
		setTitle("Create a TV Series");
		// Create music labels and text fields
		genreLabel = new JLabel("Genre:");
		directorsLabel = new JLabel("Director:");
		episodesLabel = new JLabel("Episodes:");
		starsLabel = new JLabel("Stars:");

		genreTxtField = new JTextField();
		directorTxtField = new JTextField();
		episodesSpinner = new JSpinner();
		episodesSpinner.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		starsTxtField = new JTextField();

		// Add music attributes into content panel
		contentPanel.add(genreLabel);
		contentPanel.add(genreTxtField);
		contentPanel.add(directorsLabel);
		contentPanel.add(directorTxtField);
		contentPanel.add(episodesLabel);
		contentPanel.add(episodesSpinner);
		contentPanel.add(starsLabel);
		contentPanel.add(starsTxtField);
		contentPanel.setLayout(new GridLayout(8, 2));

		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}

	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String genre = this.getGenre().getText();
		String director = this.getDirector().getText();
		int episodes = (int) this.getEpisodes().getValue();
		String stars = this.getStars().getText();
		// ProductType type, String name, float price, int year, String genre,
		// String director, int quantity, int episodes, String stars
		return new TV(ProductType.TV, p.getName(), (float) p.getPrice(),
				p.getYear(), genre, director, p.getQuantity(), episodes, stars);
	}

	public JTextField getGenre() {
		return genreTxtField;
	}

	public JTextField getDirector() {
		return directorTxtField;
	}

	public JSpinner getEpisodes() {
		return episodesSpinner;
	}

	public JTextField getStars() {
		return starsTxtField;
	}

	@Override
	public boolean validateInput() {
		if (super.validateInput()) {
			String genreTxtField = getGenre().getText();
			String directorTxtField = getDirector().getText();
			String stars = getStars().getText();
			// not null
			if (Utility.isEmpty(genreTxtField)
					|| Utility.isEmpty(directorTxtField)
					|| Utility.isEmpty(stars)) {
				return false;
			}
			// episodes must be digit
//			if (!Utility.isInteger(episodes)) {
//				return false;
//			}
			return true;
		}
		return false;
	}
}
