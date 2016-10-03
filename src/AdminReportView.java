import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * <pre>
 * This class represents the view for admin to generate report
 */
public class AdminReportView extends View {

	private static final long serialVersionUID = 1L;
	
	////// Report 
	private JPanel scrollPanel;
	public static JTable reprot;
	private static final String[] TABLE_COLUMNS = { "CustomerID", "Name", "OrderID", "Ordered date","Item name","Quantitiy of item" };

	public static final String TYPE_MUSIC = "Music";
	public static final String TYPE_MOVIE = "Movie";
	public static final String TYPE_TV = "TV";
	public static final String TYPE_GAME = "Game";
	public static final String[] TYPE = { TYPE_GAME, TYPE_MUSIC, TYPE_MOVIE,
			TYPE_TV };

	private JButton backToProductList;
	private JLabel productLabel;
	private JLabel timePeriodFromLabel;
	private JLabel timePeriodToLabel;
	
	private JLabel dateFormateLabel;

	private JTextField productNameField;
	private JTextField start;
	private JTextField end;
	
	private JButton generateReport;

	private JComboBox<Customer> customerComboBox;

	public AdminReportView() {
		// create container
		// Container container = this.getContentPane();
		// setTitle("Generate Report");
		// setBounds(100, 100, 500, 500);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(inputPanel, BorderLayout.NORTH);

		// Create button
		backToProductList = new JButton("<< Back");
		backToProductList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().showAdminProductList();
			}
		});
		
		// Create Labels
		this.dateFormateLabel = new JLabel("Date format:2016/09/30  ");
		this.productLabel = new JLabel("Product name:");
		this.timePeriodFromLabel = new JLabel("Start Time:");
		this.timePeriodToLabel = new JLabel("End Time:");
		

		this.productNameField = new JTextField();
		this.start = new JTextField();
		this.end = new JTextField();

		inputPanel.add(backToProductList);
		inputPanel.add(productLabel);
		inputPanel.add(productNameField);
		inputPanel.add(dateFormateLabel);
		inputPanel.add(timePeriodFromLabel);
		inputPanel.add(start);
		inputPanel.add(timePeriodToLabel);
		inputPanel.add(end);
		
		this.generateReport = new JButton("Report");
		inputPanel.add(generateReport);
		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		// //////////////////
		// create table
		this.reprot = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
		this.reprot.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// set different columns width
		TableColumnModel propertyTableColumnModel = this.reprot
				.getColumnModel();
		propertyTableColumnModel.getColumn(0).setPreferredWidth(100);
		propertyTableColumnModel.getColumn(1).setPreferredWidth(100);
		propertyTableColumnModel.getColumn(2).setPreferredWidth(100);
		propertyTableColumnModel.getColumn(3).setPreferredWidth(200);
		propertyTableColumnModel.getColumn(4).setPreferredWidth(240);
		propertyTableColumnModel.getColumn(5).setPreferredWidth(100);

		// / Add product table
		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(reprot);
		scrollPanel.setLayout(new GridLayout(0, 6, 0, 0));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		add(scroll, BorderLayout.CENTER);

	}

	@Override
	public void initialize() {
		generateReport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String productName = getProductNameField().getText();
				String startTime = getStart().getText();
				String endTime = getEnd().getText();
				List<AdminReport> reports = getController().generateReport(productName, startTime, endTime);
				clearReportTable();
				for (AdminReport ar : reports) {
					((DefaultTableModel) AdminReportView.getReprot().getModel())
							.addRow(new Object[] {ar.getCustomerId(),ar.getCustomerName(),ar.getOrderId(),ar.getOrderedDate(),ar.getItemName(),ar.getQuantityOfItem() });
				}
			}
		});
		
	}

	public void clearReportTable() {
		int numberOfRow = this.reprot.getModel().getRowCount();
		if (numberOfRow > 0) {
			DefaultTableModel tableModel = (DefaultTableModel) this.reprot
					.getModel();
			for (int index = (numberOfRow - 1); index >= 0; index--) {
				tableModel.removeRow(index);
			}
		}
	}
	public JTextField getProductNameField() {
		return productNameField;
	}

	public JTextField getStart() {
		return start;
	}

	public JTextField getEnd() {
		return end;
	}

	public static JTable getReprot() {
		return reprot;
	}

}
