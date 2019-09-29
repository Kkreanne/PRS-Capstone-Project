package prs_capstone;

public class User {
	
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private boolean reviewer;
	private boolean admin;

public User() {
	id = 0;
	userName = "";
	password = "";
	firstName = "";
	lastName = "";
	phoneNumber = "";
	email = "";
	reviewer = true;
	admin = false;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
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

public boolean isReviewer() {
	return reviewer;
}

public void setReviewer(boolean reviewer) {
	this.reviewer = reviewer;
}

public boolean isAdmin() {
	return admin;
}

public void setAdmin(boolean admin) {
	this.admin = admin;
}

public String toString() {
	String message = "";
	return message;
}

}