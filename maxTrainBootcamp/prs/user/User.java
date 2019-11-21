package com.maxTrainBootcamp.prs.user;

import javax.persistence.*;

@Entity //creates a table in the db, defines this class as an entity (Spring/JPA manages all of the code)
@Table(uniqueConstraints=@UniqueConstraint(name = "UIDX_username", columnNames = {"username"}))
public class User {
	@Id //primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Spring tells database to generate this for us - unique
	private int id;
	@Column(length=20, nullable=false)
	private String username;
	@Column(length=10, nullable=false)
	private String password;
	@Column(length=20, nullable=false)
	private String firstname;
	@Column(length=20, nullable=false)
	private String lastname;
	@Column(length=12, nullable=false)
	private String phone;
	@Column(length=75, nullable=false)
	private String email;
	private boolean isReviwer;
	private boolean isAdmin;
	@Column(name = "isActive") //in our class it will be active but in the db it will be isActive
	private boolean active;
	
	public User() {
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isReviwer() {
		return isReviwer;
	}
	public void setReviwer(boolean isReviwer) {
		this.isReviwer = isReviwer;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
		
}