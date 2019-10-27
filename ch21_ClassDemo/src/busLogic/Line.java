package busLogic;

public class Line {

	private int id;
	private int purchaseRequestID;
	private int productID;
	private int quantity;

	public Line() {
		id = 0;
		purchaseRequestID = 0;
		productID = 0;
		quantity = 0;
	}

	public Line(int id, int purchaseRequestID, int productID, int quantity) {
		this.id = id;
		this.purchaseRequestID = purchaseRequestID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseRequestID() {
		return purchaseRequestID;
	}

	public void setPurchaseRequestID(int purchaseRequestID) {
		this.purchaseRequestID = purchaseRequestID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		String message = "Purchase Request ID: " + purchaseRequestID + "\n" +
						"Product ID: " + productID + "\n" +
						"Quantity: " + quantity;
		return message;
	}
}