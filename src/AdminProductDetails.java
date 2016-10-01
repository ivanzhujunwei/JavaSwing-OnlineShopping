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
import javax.swing.JOptionPane;
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

	private JComboBox<String[]> productTypeComboBox;
	private JTextField productNameTextField;
	private JSpinner priceSpinner;
	private JSpinner quantitySpinner;
	private JSpinner yearSpinner;

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
	private JLabel musicGenreLabel;
	private JLabel numOfSongsLabel;
	private JTextField singer;
	private JTextField genre;
	private JSpinner numOfSongs;

	// TV
//	private final JPanel TVPanel = new JPanel();
//	private JLabel TVGenreLabel;
//	private JLabel TVDirectorLabel;
//	private JLabel episodesLabel;
//	private JLabel starLabel;
//	private JTextField TVGenre;
//	private JTextField TVDirector;
//	private JSpinner episodes;

	// Movie
//	private final JPanel moviePanel = new JPanel();
//	private JLabel movieGenreLabel;
//	private JLabel movieDirectorLabel;
//	private JLabel numberOfFilmsLabel;
//	private JTextField movieGenre;
//	private JTextField movieDirector;
//	private JSpinner numberOfFilms;

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
		setTitle("Crteate a product");
		setBounds(100, 100, 500, 600);

		container.setLayout(new BorderLayout());
		// create labels
		this.typeLabel = new JLabel("Prodcut type");
		this.nameLabel = new JLabel("Name");
		this.priceLabel = new JLabel("Price");
		this.quantityLabel = new JLabel("Quantity");
		this.yearLabel = new JLabel("Year");

		// create input element
		this.productTypeComboBox = new JComboBox(TYPE);
		this.productNameTextField = new JTextField();
		this.priceSpinner = new JSpinner();
		priceSpinner.setModel(new SpinnerNumberModel(100.0, 0.0, 10000.0, 1.0));
		this.quantitySpinner = new JSpinner();
		quantitySpinner.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		this.yearSpinner = new JSpinner();
		yearSpinner.setModel(new SpinnerNumberModel(2016, 1999, 10000, 1));

		// Create game labels and text fields
		platformLabel = new JLabel("Platform");
		publisherLabel = new JLabel("Publisher");
		platform = new JTextField();
		publisher = new JTextField();

		// Create music labels and text fields
		singerLabel = new JLabel("Singer");
		musicGenreLabel = new JLabel("Genre");
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
		this.publicPanel.add(productTypeComboBox);
		this.publicPanel.add(nameLabel);
		this.publicPanel.add(productNameTextField);
		this.publicPanel.add(priceLabel);
		this.publicPanel.add(priceSpinner);
		this.publicPanel.add(quantityLabel);
		this.publicPanel.add(quantitySpinner);
		this.publicPanel.add(yearLabel);
		this.publicPanel.add(yearSpinner);
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
		this.musicPanel.add(musicGenreLabel);
		this.musicPanel.add(genre);
		this.musicPanel.add(numOfSongsLabel);
		this.musicPanel.add(numOfSongs);
		this.musicPanel.setLayout(new GridLayout(3,2));
		this.musicPanel.setPreferredSize(new Dimension(30, 5));
		//this.musicPanel.setVisible(false);
		//contentPanel.add(musicPanel);
		
		// Add movie attributes into content panel
		// TODO 
		
		// Add TV attributes into content panel
		// TODO

		this.contentPanel.setLayout(new GridLayout(3, 1));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setVisible(true);
		container.add(contentPanel, BorderLayout.NORTH);

		productTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String typeStr = (String) getProductType().getSelectedItem();
				switch(typeStr){
				case TYPE_GAME:
					//getPlatform().setVisible(true);
					//getPlatformLabel().setVisible(true);
//					getGamePanel().setVisible(true);
//					getMusicPanel().setVisible(false);
					contentPanel.removeAll();
					publicPanel.setVisible(true);
					contentPanel.add(publicPanel);
					gamePanel.setVisible(true);
					contentPanel.add(gamePanel);
					break;
				case TYPE_MUSIC:
					//getPlatform().setVisible(false);
					//getPlatformLabel().setVisible(false);
//					getMusicPanel().setVisible(true);
//					getGamePanel().setVisible(false);
					contentPanel.removeAll();
					publicPanel.setVisible(true);
					contentPanel.add(publicPanel);
					musicPanel.setVisible(true);
					contentPanel.add(musicPanel);
					break;
				}
				pack();
			}

		});
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			container.add(buttonPanel, BorderLayout.SOUTH);
			{
				final JDialog me = this;
				JButton saveButton = new JButton("Save");
				//saveButton.setActionCommand("OK");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						c.updateUserDetails(toCustomer(c
//								.getCurrentCustomerDetails()));
//						if(c.addProductToSystem(p) == true)
//							JOptionPane.showMessageDialog(null, "New product saved Sucessfully");
						me.dispose();
					}
				});
				buttonPanel.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
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
				buttonPanel.add(cancelButton);
			}
		}
	}
	//
//	public Product saveInfoInTextFieldAsProduct()
//	{
//		String name = productNameTextField.getText();
//		float price = (float) priceSpinner.getValue();
//		int quantity = (int) quantitySpinner.getValue();
//		String typeStr = (String) getProductType().getSelectedItem();
//		
//		
//		Product p = new Product(ProductType.GAME);
//	}

	public JComboBox getProductType() {
		return productTypeComboBox;
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

	public JSpinner getPriceSpinner() {
		return priceSpinner;
	}

	public JSpinner getQuantitySpinner() {
		return quantitySpinner;
	}

	public JSpinner getYearSpinner() {
		return yearSpinner;
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

	public JLabel getmusicGenreLabel() {
		return musicGenreLabel;
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
