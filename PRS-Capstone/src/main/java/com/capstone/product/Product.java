package com.capstone.product;

import javax.persistence.*;

import com.capstone.vendor.Vendor;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_vendor_part", columnNames= {"partNumber", "vendorId"}))
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=50, nullable=false)
	private String partNumber;
	@Column(length=80, nullable=false)
	private String Name;
	@Column(columnDefinition="decimal(10, 2 NOT NULL DEFAULT 0.0")
	private double price;
	@Column(length=10, nullable=false)
	private String unit;
	@Column(length=255, nullable=true)
	private String photoPath;
	@ManyToOne(optional=false)
	@JoinColumn(name="vendorId")
	private Vendor vendor;
	
	public Product() {
		
	}
	
	public Product(int id, String partNumber, String name, double price, String unit, String photoPath, Vendor vendor) {
		super();
		this.id = id;
		this.partNumber = partNumber;
		Name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
		this.vendor = vendor;
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
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", partNumber=" + partNumber + ", Name=" + Name + ", price=" + price + ", unit="
				+ unit + ", photoPath=" + photoPath + ", vendor=" + vendor + "]";
	}
}
