package com.maxTrainBootcamp.prs.product;

import javax.persistence.*;

import com.maxTrainBootcamp.prs.vendor.Vendor;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name = "UIDX_partNumber", columnNames = ("partNumber")))
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=50, nullable=false)
	private String partNumber;
	@Column(length=80, nullable=false)
	private String name;
	@Column(columnDefinition="decimal(10, 2) NOT NULL DEFAULT 0.0") //defines what price will be defined in the db
	private double price;
	@Column(length=10, nullable=false)
	private String unit;
	@Column(length=255, nullable=true)
	private String photoPath;
	@ManyToOne(optional=false) //defines foreign key, many products can be attached to this one vendor | optional is false - defines whether foreign key is allowed to be null
	@JoinColumn(name="vendorId") //this is the id field that matches in Vendor table
	private Vendor vendor; // need this to see the vendor instance of the product
	
	public Product() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
}
