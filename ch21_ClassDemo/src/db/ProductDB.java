package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import busLogic.Product;
import util.Console;

public class ProductDB {
	
	private Connection getConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/prs?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "prs_user";
		String pwd = "sesame"; // in real life, would not show pwds, would need to encrypt
		Connection connection = DriverManager.getConnection(dbURL, username, pwd); // establishing connection to
																					// database
		return connection;
	}
	
	public List<Product> list() throws SQLException {
		Statement stmt = getConnection().createStatement();
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM Product";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			int vendorID = rs.getInt("vendorID");
			String partNumber = rs.getString("partNumber");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			String unit = rs.getString("unit");
			String photoPath = rs.getString("photoPath");
			Product p = new Product(id, vendorID, partNumber, name, price, unit, photoPath);

			products.add(p);
		}
		rs.close();
		return products;
	}
	
	public Product get(int id) throws SQLException {
		Statement stmt = getConnection().createStatement();
		Product product = null;
		String sql = "SELECT * FROM Product " + "WHERE ID = " + id;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int vendorID = rs.getInt("vendorID");
			String partNumber = rs.getString("partNumber");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			String unit = rs.getString("unit");
			String photoPath = rs.getString("photoPath");
			product = new Product(id, vendorID, partNumber, name, price, unit, photoPath);
		} else {
			// System.out.println("There is no vendor with that ID, please try again.");
		}
		rs.close();
		return product;
	}
	
	public void addProduct() throws SQLException {
		Statement stmt = getConnection().createStatement();
		System.out.println("\nLET'S ADD A PRODUCT");

		while (true) {
			int id = Console.getInt("ID: ");
			Product p = get(id);
			if (p == null) {
				String sql = "INSERT INTO product (ID, vendorID, partNumber, name, price, unit, photoPath) VALUES (";
				int vendorID = Console.getInt("Vendor ID: ");
				String partNumber = Console.getString("Part Number: ");
				String name = Console.getString("Part Name: ");
				double price = Console.getDouble("Part Price: ");
				String unit = Console.getString("Part Unit: ");
				String photoPath = Console.getString("Photo Path: ");
				int update = stmt.executeUpdate(sql + id + ",'" + vendorID + "','" + partNumber + "','" + name + "','" + price
						+ "','" + unit + "','" + photoPath + ")");
				System.out.println("Product was successfully added.\n");
			} else {
				System.out.println("That is already an ID. Please try again.");
			}
		}
	}
	
	public int updateProduct(Product p) {
		int rowCount = 0;
		String query = "update product set price = ? " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setDouble(1, p.getPrice());
			ps.setInt(2, p.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deleteProduct(Product p) {
		int rowCount = 0;
		String query = "delete from product " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setInt(1, p.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}