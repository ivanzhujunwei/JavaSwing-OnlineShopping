import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdminProductDetails extends JDialog {

	private static final long serialVersionUID = 1L;

	// //////////
	// public attributes
	private final JPanel publicPanel = new JPanel();
	private final JPanel contentPanel = new JPanel();
	private JLabel typeLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;
	private JLabel yearLabel;

	private JComboBox productType;
	private JTextField productName;
	private JSpinner price;
	private JSpinner quantity;
	private JSpinner year;

	// ///////////
	// Type content
	public static final String TYPE_MUSIC = "Music";
	public static final String TYPE_MOVIE = "Movie";
	public static final String TYPE_TV = "TV";
	public static final String TYPE_GAME = "Game";
	public static final String[] TYPE = { TYPE_GAME, TYPE_MUSIC, TYPE_MOVIE,
			TYPE_TV };

	// ////////////
	// game
	private final JPanel gamePanel = new JPanel();
	private JLabel platformLabel;
	private JLabel publisherLabel;
	private JTextField platform;
	private JTextField publisher;

	// music
	private final JPanel musicPanel = new JPanel();
	private JLabel singerLabel;
	private JLabel genreLabel;
	private JLabel numOfSongsLabel;
	private JTextField singer;
	private JTextField genre;
	private JSpinner numOfSongs;

	// TV
	// TODO:

	// Movie
	// TODO:

	public static void display(ShopController c) {
		AdminProductDetails dialog = new AdminProductDetails(c);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}

	public Customer toCustomer(Customer customer) {
		// customer.setAddress(homeAddr.getText());
		// customer.setName(fullName.getText());
		// customer.setCardNumber(cardNum.getText());
		// customer.setPhone(phoneNumber.getText());
		return customer;
		// return new Customer(fullName.getText(), homeAddr.getText(),
		// cardNum.getText(), phoneNumber.getText());
	}

	public AdminProductDetails(final ShopController c) {

		// Customer user = c.getCurrentCustomerDetails();
		Container container = this.getContentPane();
		setTitle("User Information");
		setBounds(100, 100, 450, 300);

		container.setLayout(new BorderLayout());
		// create labels
		this.typeLabel = new JLabel("Prodcut type");
		this.nameLabel = new JLabel("Name");
		this.priceLabel = new JLabel("Price");
		this.quantityLabel = new JLabel("Quantity");
		this.yearLabel = new JLabel("Year");

		// create input element
		this.productType = new JComboBox(TYPE);
		this.productName = new JTextField();
		this.price = new JSpinner();
		price.setModel(new SpinnerNumberModel(100.0, 0.0, 10000.0, 1.0));
		this.quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		this.year = new JSpinner();
		year.setModel(new SpinnerNumberModel(2016, 1999, 10000, 1));

		// Create game labels and text fields
		platformLabel = new JLabel("Platform");
		publisherLabel = new JLabel("Publisher");
		platform = new JTextField();
		publisher = new JTextField();

		// Create music labels and text fields
		singerLabel = new JLabel("Singer");
		genreLabel = new JLabel("Genre");
		numOfSongsLabel = new JLabel("NO. of songs");
		singer = new JTextField();
		genre = new JTextField();
		numOfSongs = new JSpinner();
		numOfSongs.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		
		// Create movie labels and text fields
		// TODO
		
		// Create TV labels and text fields
		// TODO

		// Add public attributes to content panel
		this.publicPanel.add(typeLabel);
		this.publicPanel.add(productType);
		this.publicPanel.add(nameLabel);
		this.publicPanel.add(productName);
		this.publicPanel.add(priceLabel);
		this.publicPanel.add(price);
		this.publicPanel.add(quantityLabel);
		this.publicPanel.add(quantity);
		this.publicPanel.add(yearLabel);
		this.publicPanel.add(year);
		publicPanel.setLayout(new GridLayout(5,2));
		contentPanel.add(publicPanel);

		// Add game attributes into content panel
		this.gamePanel.add(platformLabel);
		this.gamePanel.add(platform);
		this.gamePanel.add(publisherLabel);
		this.gamePanel.add(publisher);
		gamePanel.setLayout(new GridLayout(2,2));
		this.gamePanel.setPreferredSize(new Dimension(30, 5));
		contentPanel.add(gamePanel);
		
		// Add music attributes into content panel
		this.musicPanel.add(singerLabel);
		this.musicPanel.add(singer);
		this.musicPanel.add(genreLabel);
		this.musicPanel.add(genre);
		this.musicPanel.add(numOfSongsLabel);
		this.musicPanel.add(numOfSongs);
		this.musicPanel.setLayout(new GridLayout(3,2));
		this.musicPanel.setVisible(false);
		contentPanel.add(musicPanel);
		
		// Add movie attributes into content panel
		// TODO 
		
		// Add TV attributes into content panel
		// TODO

		this.contentPanel.setLayout(new GridLayout(3, 1));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		container.add(contentPanel, BorderLayout.NORTH);

		productType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String typeStr = (String) getProductType().getSelectedItem();
				switch(typeStr){
				case TYPE_GAME:
					getPlatform().setVisible(true);
					getPlatformLabel().setVisible(true);
//					getGamePanel().setVisible(true);
//					getMusicPanel().setVisible(false);
					break;
				case TYPE_MUSIC:
					getPlatform().setVisible(false);
					getPlatformLabel().setVisible(false);
//					getMusicPanel().setVisible(true);
//					getGamePanel().setVisible(false);
					break;
				}
				pack();
			}

		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JDialog me = this;
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.updateUserDetails(toCustomer(c
								.getCurrentCustomerDetails()));
						me.dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JComboBox getProductType() {
		return productType;
	}

	public JLabel getPlatformLabel() {
		return platformLabel;
	}

	public JTextField getPlatform() {
		return platform;
	}

	public JLabel getTypeLabel() {
		return typeLabel;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getPriceLabel() {
		return priceLabel;
	}

	public JLabel getQuantityLabel() {
		return quantityLabel;
	}

	public JLabel getYearLabel() {
		return yearLabel;
	}

//	public JTextField getName() {
//		return productName;
//	}

	public JSpinner getPrice() {
		return price;
	}

	public JSpinner getQuantity() {
		return quantity;
	}

	public JSpinner getYear() {
		return year;
	}

	public JLabel getPublisherLabel() {
		return publisherLabel;
	}

	public JTextField getPublisher() {
		return publisher;
	}

	public JLabel getSingerLabel() {
		return singerLabel;
	}

	public JLabel getGenreLabel() {
		return genreLabel;
	}

	public JLabel getNumOfSongsLabel() {
		return numOfSongsLabel;
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

	public JPanel getMusicPanel() {
		return musicPanel;
	}
	public JPanel getGamePanel() {
		return gamePanel;
	}

}
