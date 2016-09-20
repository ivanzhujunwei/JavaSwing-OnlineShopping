package gui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import beans.Game;
import beans.Product;
import source.ProductThumbnail;

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
//				getController().showLogout();
				int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to logout?","Confirm", 2);
				if(dialogResult == JOptionPane.YES_OPTION){
					// save data
					// cart data
					// product data
					List<Product> list = getController().getBackend().getProducts();
					String saveData = "";
//					for (Product product : list)
//					{
//						if (product instanceof Game){
//							saveData+=
//						}
//						ArrayList<Book> bookList = borrower.getBookList();
//						saveData += product.get
//						for (Book book : bookList)
//							saveData += FileHandle.SPLIT_COMMA + book.toString();
//						saveData += "\n";
//					}
//					FileHandle.writeToFile(saveData);
					System.out.println("You have exited, thank you!");
					System.exit(0);
					// redirect to log in view
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
