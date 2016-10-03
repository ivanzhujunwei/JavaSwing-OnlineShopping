import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a music product into system.
 */
public class AdminMusicDetails extends AdminProductDetails {

	// music
	private JLabel singerLabel;
	private JLabel genreLabel;
	private JLabel numOfSongsLabel;
	private JTextField singer;
	private JTextField genre;
	private JSpinner numOfSongs;

	public AdminMusicDetails(ShopController c,DefaultTableModel tm) {
		super(c,tm);
		setTitle("Create a music");
		// Create music labels and text fields
		singerLabel = new JLabel("Singer");
		genreLabel = new JLabel("Genre");
		numOfSongsLabel = new JLabel("NO. of songs");
		singer = new JTextField();
		genre = new JTextField();
		numOfSongs = new JSpinner();
		numOfSongs.setModel(new SpinnerNumberModel(1, 1, 10000, 1));

		// Add music attributes into content panel
		contentPanel.add(singerLabel);
		contentPanel.add(singer);
		contentPanel.add(genreLabel);
		contentPanel.add(genre);
		contentPanel.add(numOfSongsLabel);
		contentPanel.add(numOfSongs);
		contentPanel.setLayout(new GridLayout(7, 2));
		
		contentPanel.setLayout(new GridLayout(7, 2));
		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}
	
	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String singer = this.getSinger().getText();
		String genre = this.getGenre().getText();
		int numOfSongs = (int) this.getNumOfSongs().getValue();
		// ProductType type, String name, float price, int year, String genre, String singer, int quantity, int numOfSongs
		return new Music(ProductType.MUSIC, p.getName(),p.getPrice(), p.getYear(), genre, singer, p.getQuantity(), numOfSongs);
	}

	public JTextField getSinger() {
		return singer;
	}

	public JTextField getGenre() {
		return genre;
	}

	public JSpinner getNumOfSongs() {
		return numOfSongs;
	}
	
	@Override
	public boolean validateInput(){
		if(super.validateInput()){
			String genreTxtField = getGenre().getText();
			String singerTxtField = getSinger().getText();
			// not null
			if(Utility.isEmpty(genreTxtField) || Utility.isEmpty(singerTxtField) || Utility.isEmpty(numOfSongs)){
				return false;
			}
			// numofFilms must be digit
//			if (!Utility.isInteger(numOfSongs)){
//				return false;
//			}
			return true;
		}
		return false;
	}
}
