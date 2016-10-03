import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a order entity
 */
public class Order {
//	jn319,2016/09/22 15:41:09,Pokemon Sun,1,The Shawshank Redemption,1,
//	jn319,2016/09/22 15:42:18,The Shawshank Redemption,3,Call of Duty,2,
//	jn319,2016/09/30 12:34:00,Counter-strike,1,
	private int orderId;
	private String customerId;
	private String orderedDate;
	private List<CartItem> orderItem;
	
	public Order(){
		orderItem = new ArrayList<CartItem>();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public List<CartItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<CartItem> orderItem) {
		this.orderItem = orderItem;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
