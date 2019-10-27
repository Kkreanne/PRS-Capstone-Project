package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import busLogic.User;
import util.Console;

public class UserDB {

	private Connection getConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/prs?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "prs_user";
		String pwd = "sesame"; // in real life, would not show pwds, would need to encrypt
		Connection connection = DriverManager.getConnection(dbURL, username, pwd); // establishing connection to
																					// database
		return connection;
	}

	public List<User> list() throws SQLException {
		Statement stmt = getConnection().createStatement();
		String sql = "SELECT * FROM User";
		List<User> users = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			// parse user row into a user POJO
			int id = rs.getInt("id");
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");
			boolean reviewer = rs.getBoolean("isReviewer");
			boolean admin = rs.getBoolean("isAdmin");
			User u = new User(id, userName, password, firstName, lastName, phoneNumber, email, reviewer, admin); // create
																														// instance
																													// of
																													// users
			users.add(u); // add users to table
		}
		rs.close();
		return users;
	}

	public User get(int id) throws SQLException {
		Statement stmt = getConnection().createStatement();
		String sql = "SELECT * FROM User " + "WHERE ID = " + id;
		User user = null;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			// parse user row into a user POJO
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");
			boolean reviewer = rs.getBoolean("isReviewer");
			boolean admin = rs.getBoolean("isAdmin");
			user = new User(id, userName, password, firstName, lastName, phoneNumber, email, reviewer, admin); // users
		} 
		rs.close();
		return user;
	}

	public void addUser() throws SQLException {
		Statement stmt = getConnection().createStatement();
		System.out.println("\nLET'S ADD A USER");

		while (true) {
			int id = Console.getInt("ID: ");
			User u = get(id);
			if (u == null) {
				String sql = "INSERT INTO user (ID, UserName, Password, FirstName, LastName, PhoneNumber, Email, IsReviewer, IsAdmin) VALUES (";
				String uname = Console.getString("Username: ");
				String pwd = Console.getString("Password: ");
				String fname = Console.getString("First Name: ");
				String lname = Console.getString("Last Name: ");
				String phone = Console.getString("Phone number(with dashes): ");
				String email = Console.getString("Email: ");
				int rvw = Console.getInt("Is this user a reviewer? (1 for yes, 0 for no): ", -1, 2);
				int admin = Console.getInt("Is this user an admin? (1 for yes, 0 for no): ", -1, 2);
				int update = stmt.executeUpdate(sql + id + ",'" + uname + "','" + pwd + "','" + fname + "','" + lname
						+ "','" + phone + "','" + email + "'," + rvw + "," + admin + ")");
				System.out.println("User successfully added.\n");
			} else {
				System.out.println("That is already an ID. Please try again.");
			}
		}
	}

	public int updateUser(User u) {
		int rowCount = 0;
		String query = "update user set email = ? " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, u.getEmail());
			ps.setInt(2, u.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deleteUser(User u) {
		int rowCount = 0;
		String query = "delete from user " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setInt(1, u.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}