/***
 * Admin report entity
 * @author zjw
 *
 */
public class AdminReport {
	//CustomerID", "Name", "OrderID", "Ordered date","Item name","Quantitiy of item
	private String customerId;
	private String customerName;
	private String orderId;
	private String orderedDate;
	private String itemName;
	private String quantityOfItem;
	
	public AdminReport(){
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getQuantityOfItem() {
		return quantityOfItem;
	}

	public void setQuantityOfItem(String quantityOfItem) {
		this.quantityOfItem = quantityOfItem;
	}
	
	
}
