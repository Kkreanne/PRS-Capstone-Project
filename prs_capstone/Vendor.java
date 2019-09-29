package prs_capstone;

public class Vendor {
	
	private int id;
	private String code;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;
	private boolean preApproved;

public Vendor() {
	id = 0;
	code = "";
	name = "";
	address = "";
	city = "";
	state = "";
	zip = "";
	phoneNumber = "";
	email = "";
	preApproved = false;
	}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getZip() {
	return zip;
}

public void setZip(String zip) {
	this.zip = zip;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public boolean isPreApproved() {
	return preApproved;
}

public void setPreApproved(boolean preApproved) {
	this.preApproved = preApproved;
}

public String toString() {
	String message = "";
	return message;
}

}
