package com.maxTrainBootcamp.prs.lineItem;

import javax.persistence.*;
import com.maxTrainBootcamp.prs.product.Product;
import com.maxTrainBootcamp.prs.request.Request;

@Entity
@Table
public class LineItem {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional=false)
	@JoinColumn(name="requestId")
	private Request request;
	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product;
	@Column(length=100, nullable=false)
	private int quantity;
	
	public LineItem() {
		
	}

	public LineItem(int id, Request request, Product product, int quantity) {
		super();
		this.id = id;
		this.request = request;
		this.product = product;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
