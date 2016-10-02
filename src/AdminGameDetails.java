import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a game product into system.
 */
public class AdminGameDetails extends AdminProductDetails {
	// ////////////
	// game
	private JLabel platformLabel;
	private JLabel publisherLabel;
	private JTextField platform;
	private JTextField publisher;

	public AdminGameDetails(ShopController c, DefaultTableModel tm) {
		super(c,tm);
		setTitle("Create a game");
		// Create game labels and text fields
		platformLabel = new JLabel("Platform");
		publisherLabel = new JLabel("Publisher");
		platform = new JTextField();
		publisher = new JTextField();

		// Add game attributes into content panel
		contentPanel.add(platformLabel);
		contentPanel.add(platform);
		contentPanel.add(publisherLabel);
		contentPanel.add(publisher);

		contentPanel.setLayout(new GridLayout(6, 2));
		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}

	public JTextField getPlatform() {
		return platform;
	}

	public JTextField getPublisher() {
		return publisher;
	}

	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String publisher = this.getPublisher().getText();
		String platform = this.getPlatform().getText();
		// ProductType type, String name,int year,String platform, float price,String publisher,int quantity
		return new Game(ProductType.GAME, p.getName(), p.getYear(), platform, p.getPrice(), publisher, p.getQuantity());
	}
	
	@Override
	public boolean validateInput(){
		if(super.validateInput()){
			String platform = getPlatform().getText();
			String publisher = getPublisher().getText();
			// not null
			if(Utility.isEmpty(platform) || Utility.isEmpty(publisher)){
				return false;
			}
			return true;
		}
		return false;
	}
}
