package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import busLogic.Vendor;
import util.Console;

public class VendorDB {

	private Connection getConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/prs?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "prs_user";
		String pwd = "sesame"; // in real life, would not show pwds, would need to encrypt
		Connection connection = DriverManager.getConnection(dbURL, username, pwd); // establishing connection to
																					// database
		return connection;
	}

	public List<Vendor> list() throws SQLException {
		Statement stmt = getConnection().createStatement();
		List<Vendor> vendors = new ArrayList<>();
		String sql = "SELECT * FROM Vendor";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String code = rs.getString("code");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zip = rs.getString("zip");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");
			Vendor v = new Vendor(id, code, name, address, city, state, zip, phoneNumber, email);

			vendors.add(v);
		}
		rs.close();
		return vendors;
	}

	public Vendor get(int id) throws SQLException {
		Statement stmt = getConnection().createStatement();
		Vendor vendors = null;
		String sql = "SELECT * FROM Vendor " + "WHERE ID = " + id;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			String code = rs.getString("code");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zip = rs.getString("zip");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");
			vendors = new Vendor(id, code, name, address, city, state, zip, phoneNumber, email);
		} else {
			// System.out.println("There is no vendor with that ID, please try again.");
		}
		rs.close();
		return vendors;
	}

	public void addVendor() throws SQLException {
		Statement stmt = getConnection().createStatement();
		System.out.println("\nLET'S ADD A VENDOR");

		while (true) {
			int id = Console.getInt("ID: ");
			Vendor v = get(id);
			if (v == null) {
				String sql = "INSERT INTO Vendor (ID, code, name, address, city, state, zip, phoneNumber, email) VALUES (";
				String code = Console.getString("Code: ");
				String name = Console.getString("Name: ");
				String address = Console.getString("Address: ");
				String city = Console.getString("City: ");
				String state = Console.getString("State: ");
				String zip = Console.getString("Zip: ");
				String phoneNumber = Console.getString("Phone number(with dashes): ");
				String email = Console.getString("Email: ");
				int update = stmt.executeUpdate(sql + id + ",'" + code + "','" + name + "','" + address + "','" + city
						+ "','" + state + "','" + zip + "','" + phoneNumber + "','" + email + "')");
				System.out.println("Vendor " +v+ "has been successfully added.\n");
			} else {
				System.out.println("That is already an ID. Please try again.");
				continue;
			}
		}
	}

	public void add() {

	}
	
	public int updateVendor(Vendor v) {
		int rowCount = 0;
		String query = "update vendor set email = ? " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, v.getEmail());
			ps.setInt(2, v.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deleteVendor(Vendor v) {
		int rowCount = 0;
		String query = "delete from vendor " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setInt(1, v.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}