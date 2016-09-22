


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class ProductListView extends View {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel scrollPanel;
	
	public ProductListView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel, BorderLayout.NORTH);
		
		JButton myInfoButton = new JButton("My account");
		panel.add(myInfoButton);
		
		myInfoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UserDetails.display(getController());
			}
		});
		
		JButton cartButton = new JButton("View cart");
		
		cartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				getController().showCartView();
			}
		});
		
		panel.add(cartButton);
		
		//////////// log out
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to logout?","Confirm", 2);
				if(dialogResult == JOptionPane.YES_OPTION){
					// save cart data
					getController().storeCartData();
					// redirect to log in view
					getController().getWindow().dispose();
					getController().init();
				}
			}
		});
		panel.add(logoutBtn);
		////////////////////
		
		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(scrollPanel);
		scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);

	}

	public void initialize() {
		scrollPanel.removeAll();
		List<Product> list = getController().getBackend().getProducts();
		for(Product p : list){
			scrollPanel.add(new ProductThumbnail(getController(), p));
		}
		revalidate();
	}

}
