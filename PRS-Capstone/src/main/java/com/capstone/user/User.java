package com.capstone.user;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name = "UIDX_username", columnNames = {"username"}))
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=20, nullable=false)
	private String username;
	@Column(length=10, nullable=false)
	private String password;
	@Column(length=20, nullable=false)
	private String firstName;
	@Column(length=20, nullable=false)
	private String lastName;
	@Column(length=12, nullable=false)
	private String phone;
	@Column(length=75, nullable=false)
	private String Email;
	private boolean isAdmin;
	private boolean isReviewer;
	
	public User() {
	}

	public User(String username, String password, String firstName, String lastName, String phone, String email,
			boolean isAdmin, boolean isReviewer) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		Email = email;
		this.isAdmin = isAdmin;
		this.isReviewer = isReviewer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isReviewer() {
		return isReviewer;
	}

	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phone=" + phone + ", Email=" + Email + ", isAdmin=" + isAdmin
				+ ", isReviewer=" + isReviewer + "]";
	}
	
}
