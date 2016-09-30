


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
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class AdminProductListView extends View {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel scrollPanel;
	private JTable productTable;
	private static final String[] TABLE_COLUMNS = {"Type", "Name", "Price", "Quantity"};
	
	public AdminProductListView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel, BorderLayout.NORTH);
		
		JButton createProductButton = new JButton("Create Product");
		panel.add(createProductButton);
		
		createProductButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AdminProductDetails.display(getController());
			}
		});
		
		JButton cartButton = new JButton("Generate report");
		
		cartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				getController().showCartView();
			}
		});
		
		panel.add(cartButton);
		
		//////////// log out
		JButton logoutBtn = new JButton("Lssogout");
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
		// create table
        this.productTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // set different columns width
        TableColumnModel propertyTableColumnModel = this.productTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(200);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(300);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(200);
        propertyTableColumnModel.getColumn(3).setPreferredWidth(200);
        
		/// Add product table
		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(productTable);
		scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);

	}

	public void initialize() {
		scrollPanel.removeAll();
		List<Product> list = getController().getBackend().getProducts();
		this.clearProductTable();
		for(Product p : list){
	        ((DefaultTableModel)this.productTable.getModel()).addRow(new Object[]{p.getType(), 
	                                                                               p.getName(), 
	                                                                               p.getPrice(), 
	                                                                               p.getQuantity()});
		}
		revalidate();
	}
	
	public void clearProductTable() {     
        int numberOfRow = this.productTable.getModel().getRowCount();
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.productTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }

}
