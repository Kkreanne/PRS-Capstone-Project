package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import busLogic.Line;
import util.Console;

public class LineDB {
	
	private Connection getConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/prs?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "prs_user";
		String pwd = "sesame"; // in real life, would not show pwds, would need to encrypt
		Connection connection = DriverManager.getConnection(dbURL, username, pwd); // establishing connection to
																						// database
		return connection;
	}

	public List<Line> list() throws SQLException {
		Statement stmt = getConnection().createStatement();
		List<Line> lineItems = new ArrayList<>();
		String sql = "SELECT * FROM Line";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			int requestID = rs.getInt("requestID");
			int productID = rs.getInt("productID");
			int quantity = rs.getInt("quantity");
			Line l = new Line(id, requestID, productID, quantity); 
																											
			lineItems.add(l);
		}
		rs.close();
		return lineItems;
	}

	public Line get(int id) throws SQLException {
		Statement stmt = getConnection().createStatement();
		Line line = null;
		String sql = "SELECT * FROM Line " + "WHERE ID = " + id;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int requestID = rs.getInt("requestID");
			int productID = rs.getInt("productID");
			int quantity = rs.getInt("quantity");
			line = new Line(id, requestID, productID, quantity); 
		} else {
			System.out.println("There is no line item with that ID, please try again.");
		}
		rs.close();
		return line;
	}

	public void addLine() throws SQLException {
		Statement stmt = getConnection().createStatement();
		System.out.println("\nLET'S ADD A LINE ITEM");

		while (true) {
			int id = Console.getInt("ID: ");
			Line l = get(id);
			if (l == null) {
				String sql = "INSERT INTO line (ID, requestID, productID, quantity) VALUES (";
				int requestID = Console.getInt("Request ID: ");
				int productID = Console.getInt("Product ID: ");
				int quantity = Console.getInt("Quantity: ");
				int update = stmt.executeUpdate(sql + id + ",'" + requestID + "','" + productID + "','" + quantity + ")");
				System.out.println("Line Item successfully added.\n");
			} else {
				System.out.println("That is already an ID. Please try again.");
			}
		}
	}

	public void add() {

	}

	public int updateLine(Line l) {
		int rowCount = 0;
		String query = "update line set quantity = ? " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setInt(1, l.getQuantity());
			ps.setInt(2, l.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deleteLine(Line l) {
		int rowCount = 0;
		String query = "delete from line " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setInt(1, l.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}