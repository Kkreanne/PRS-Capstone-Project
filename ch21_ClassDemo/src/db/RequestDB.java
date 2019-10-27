package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import busLogic.Request;
import util.Console;

public class RequestDB {

	private Connection getConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/prs?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "prs_user";
		String pwd = "sesame"; // in real life, would not show pwds, would need to encrypt
		Connection connection = DriverManager.getConnection(dbURL, username, pwd); // establishing connection to
																						// database
		return connection;
	}

	public List<Request> list() throws SQLException {
		Statement stmt = getConnection().createStatement();
		List<Request> requests = new ArrayList<>();
		String sql = "SELECT * FROM Request";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			int userID = rs.getInt("userID");
			String description = rs.getString("description");
			String justification = rs.getString("justification");
			Date dateNeeded = rs.getDate("dateNeeded");
			String deliveryMode = rs.getString("deliveryMode");
			String status = rs.getString("status");
			double total = rs.getDouble("total");
			Date submittedDate = rs.getDate("submittedDate");
			String reasonForRejection = rs.getString("reasonForRejection");
			Request r = new Request(id, userID, description, justification, dateNeeded, deliveryMode, status, total, submittedDate, reasonForRejection); 
																											
			requests.add(r);
		}
		rs.close();
		return requests;
	}

	public Request get(int id) throws SQLException {
		Statement stmt = getConnection().createStatement();
		Request request = null;
		String sql = "SELECT * FROM Request " + "WHERE ID = " + id;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int userID = rs.getInt("userID");
			String description = rs.getString("description");
			String justification = rs.getString("justification");
			Date dateNeeded = rs.getDate("dateNeeded");
			String deliveryMode = rs.getString("deliveryMode");
			String status = rs.getString("status");
			double total = rs.getDouble("total");
			Date submittedDate = rs.getDate("submittedDate");
			String reasonForRejection = rs.getString("reasonForRejection");
			request = new Request(id, userID, description, justification, dateNeeded, deliveryMode, status, total, submittedDate, reasonForRejection); 
		} else {
			System.out.println("There is no request with that ID, please try again.");
		}
		rs.close();
		return request;
	}

	public void addRequest() throws SQLException {
		Statement stmt = getConnection().createStatement();
		System.out.println("\nLET'S ADD A REQUEST");

		while (true) {
			int id = Console.getInt("ID: ");
			Request r = get(id);
			if (r == null) {
				String sql = "INSERT INTO request (ID, UserID, description, justification, dateNeeded, deliveryMode, status, total, submittedDate, reasonForRejection) VALUES (";
				int userID = Console.getInt("User ID: ");
				String description = Console.getString("Description: ");
				String justification = Console.getString("Justification: ");
				String dateNeeded = Console.getDate("Date Needed : ");
				String deliveryMode = Console.getString("Delivery Mode: ");
				String status = Console.getString("String: ");
				String submittedDate = Console.getDate("Date Submitted: ");
				String reasonForRejection = Console.getString("Reason for Rejection, if any: ");
				int update = stmt.executeUpdate(sql + id + ",'" + userID + "','" + description + "','" + justification + "','" + dateNeeded
						+ "','" + deliveryMode + "','" + status + "'," + submittedDate + "," + reasonForRejection + ")");
				System.out.println("Request successfully added.\n");
			} else {
				System.out.println("That is already an ID. Please try again.");
			}
		}
	}

	public void add() {

	}

	public int updateRequest(Request r) {
		int rowCount = 0;
		String query = "update request set dateNeeded = ? " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setDate(1, r.getDateNeeded());
			ps.setInt(2, r.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deleteRequest(Request r) {
		int rowCount = 0;
		String query = "delete from request " + "where id = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setInt(1, r.getId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}