package ui;

import java.sql.SQLException;
import java.util.List;

import busLogic.*;
import db.*;
import util.Console;

public class PRSConsoleApp {
	private static UserDB udb = new UserDB();
	private static VendorDB vdb = new VendorDB();
	private static ProductDB pdb = new ProductDB();
	private static RequestDB rdb = new RequestDB();
	private static LineDB ldb = new LineDB();

	public static void main(String[] args) {
		System.out.println("Welcome to the Purchase Request System Console!");
		boolean ask = true;

		while (ask == true) {
			String choice = Console.getString(
					"\nWhat would you like to choose? \n " + "\tUser - u \n" + "\tVendor - v \n" + "\tProduct - p \n"
							+ "\tRequest - r \n" + "\tLine Item - l \n" + "\tExit application - e\n");
			String action = "";
			switch (choice) {
			case "u":
			case "U":
				int command = 0;
				while (command != 99) {
					command = Console.getInt(getUserMenu(), 0, 100);
					switch (command) {
					case 1:
						// list function
						try {
							List<User> users = udb.list(); // calling user db and return all users in table
							System.out.println("List of users \n");
							for (User u : users) { // for this list of users, print every user
								System.out.println(u);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} // block scope
						break;
					case 2:
						// get
						int id = Console.getInt("User ID:  ");
						try {
							User u = udb.get(id);
							System.out.println("\nUser retrieved: ");
							System.out.println(u);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						// add
						try {
							//udb.add();
							udb.addUser();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						// update
						// 1 - get id
						System.out.println("Let's update a user");
						int uid = Console.getInt("User id: ");
						// 2 - get user from id
						User u = getUser(uid);
						if (u == null) {
							// 3 - does user exist?
							System.out.println("No user found for id: " + uid);
						} else {
							// 4 - if exists, prompt for new email *write switch for user to pick
							String newEmail = Console.getString("New Email address: ");
							// 5 - update new email
							u.setEmail(newEmail);
							// update user
							int rowCount = udb.updateUser(u);
							if (rowCount == 1) {
								System.out.println("User " + uid + " has been updated\n");
							} else {
								System.out.println("Error updating user.");
							}
						}
						break;
					case 5:
						// delete
						// 1 - get id
						System.out.println("Let's delete a user");
						uid = Console.getInt("User id: ");
						// 2 - get user from id
						u = getUser(uid);
						if (u == null) {
							// 3 - does user exist?
							System.out.println("No user found for id: " + uid);
						} else {
							// delete user
							int rowCount = udb.deleteUser(u);
							if (rowCount == 1) {
								System.out.println("User has been deleted.\n");
							} else {
								System.out.println("Error deleting user.");
							}
						}
						break;
					case 99:
						// exit
						break;
					default:
						System.out.println("Error. Invalid command.");
						break;
					}
					continue;
				}
				break;
			case "v":
			case "V":
				VendorDB vdb = new VendorDB();
				int command2 = 0;
				while (command2 != 99) {
					command2 = Console.getInt(getVendorMenu(), 0, 100);
					switch (command2) {
					case 1:
						try {
							List<Vendor> vendors = vdb.list(); 
							System.out.println("\nList of vendors: ");
							for (Vendor v : vendors) { 
								System.out.println(v);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} 
						break;
					case 2:
						int id = Console.getInt("Vendor ID:  ");
						try {
							Vendor v = vdb.get(id);
							System.out.println("\nVendor retrieved: ");
							System.out.println(v);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						try {
							vdb.addVendor();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						System.out.println("Let's update a vendor");
						int vid = Console.getInt("Vendor id: ");
						Vendor v = getVendor(vid);
						if (v == null) {
							System.out.println("No vendor found for id: " + vid);
						} else {
							String newEmail = Console.getString("New Email address: ");
							v.setEmail(newEmail);
							int rowCount = vdb.updateVendor(v);
							if (rowCount == 1) {
								System.out.println("Vendor " + vid + " has been updated\n");
							} else {
								System.out.println("Error updating vendor.");
							}
						}
						break;
					case 5:
						System.out.println("Let's delete a vendor");
						vid = Console.getInt("Vendor id: ");
						v = getVendor(vid);
						if (v == null) {
							System.out.println("No vendor found for id: " + vid);
						} else {
							int rowCount = vdb.deleteVendor(v);
							if (rowCount == 1) {
								System.out.println("Vendor has been deleted.\n");
							} else {
								System.out.println("Error deleting vendor.");
							}
						}
						break;
					case 99:
						// exit
						break;
					default:
						System.out.println("Error. Invalid command.");
						break;
					}
					continue;
				}
				break;
			case "p":
			case "P":
				int command3 = 0;
				while (command3 != 99) {
					command3 = Console.getInt(getProductMenu(), 0, 100);
					switch (command3) {
					case 1:
						try {
							List<Product> products = pdb.list();
							System.out.println("List of products \n");
							for (Product p : products) {
								System.out.println(p);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 2:
						int id = Console.getInt("Product ID:  ");
						try {
							Product p = pdb.get(id);
							System.out.println("\nProduct retrieved: ");
							System.out.println(p);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						try {
							pdb.addProduct();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						System.out.println("Let's update a product");
						int pid = Console.getInt("Product id: ");
						Product p = getProduct(pid);
						if (p == null) {
							System.out.println("No product found for id: " + pid);
						} else {
							double newPrice = Console.getDouble("New price: ");
							p.setPrice(newPrice);
							int rowCount = pdb.updateProduct(p);
							if (rowCount == 1) {
								System.out.println("Product " + pid + " has been updated\n");
							} else {
								System.out.println("Error updating product.");
							}
						}
						break;
					case 5:
						System.out.println("Let's delete a product");
						pid = Console.getInt("Product id: ");
						p = getProduct(pid);
						if (p == null) {
							System.out.println("No product found for id: " + pid);
						} else {
							int rowCount = pdb.deleteProduct(p);
							if (rowCount == 1) {
								System.out.println("Product has been deleted.\n");
							} else {
								System.out.println("Error deleting product.");
							}
						}
						break;
					case 99:
						// exit
						break;
					default:
						System.out.println("Error. Invalid command.");
						break;
					}
					continue;
				}
				break;
			/*case "r":
			case "R":
				int command4 = 0;
				while (command4 != 99) {
					command4 = Console.getInt(getRequestMenu(), 0, 100);
					switch (command4) {
					case 1:
						try {
							List<Request> requests = rdb.list();
							System.out.println("List of requests \n");
							for (Request r : requests) {
								System.out.println(r);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} 
						break;
					case 2:
						int id = Console.getInt("Request ID:  ");
						try {
							Request r = rdb.get(id);
							System.out.println("\nRequest retrieved: ");
							System.out.println(r);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						try {
							rdb.addRequest();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						System.out.println("Let's update a request");
						int rid = Console.getInt("Request id: ");
						Request r = getRequest(rid);
						if (r == null) {
							System.out.println("No request found for id: " + rid);
						} else {
							String newStatus = Console.getString("Status: ");
							r.setStatus(newStatus);
							int rowCount = rdb.updateRequest(r);
							if (rowCount == 1) {
								System.out.println("Request " + rid + " has been updated\n");
							} else {
								System.out.println("Error updating request.");
							}
						}
						break;
					case 5:
						System.out.println("Let's delete a request");
						rid = Console.getInt("Request id: ");
						r = getRequest(rid);
						if (r == null) {
							System.out.println("No request found for id: " + rid);
						} else {
							int rowCount = rdb.deleteRequest(r);
							if (rowCount == 1) {
								System.out.println("Request has been deleted.\n");
							} else {
								System.out.println("Error deleting request.");
							}
						}
						break;
					case 99:
						// exit
						break;
					default:
						System.out.println("Error. Invalid command.");
						break;
					}
					continue;
				}
				break;
			case "l":
			case "L":
				int command5 = 0;
				while (command5 != 99) {
					command5 = Console.getInt(getLineMenu(), 0, 100);
					switch (command5) {
					case 1:
						try {
							List<Line> lineItems = ldb.list();
							System.out.println("List of Line Items \n");
							for (Line l : lineItems) {
								System.out.println(l);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} 
						break;
					case 2:
						int id = Console.getInt("Line Item ID:  ");
						try {
							Line l = ldb.get(id);
							System.out.println("\nLine Item retrieved: ");
							System.out.println(l);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						try {
							ldb.addLine();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						System.out.println("Let's update a line item");
						int lid = Console.getInt("Line Item id: ");
						Line l = getLine(lid);
						if (l == null) {
							System.out.println("No line item found for id: " + lid);
						} else {
							int newQuantity = Console.getInt("Quantity: ");
							l.setQuantity(newQuantity);
							int rowCount = ldb.updateLine(l);
							if (rowCount == 1) {
								System.out.println("Line Item " + lid + " has been updated\n");
							} else {
								System.out.println("Error updating line item.");
							}
						}
						break;
					case 5:
						System.out.println("Let's delete a line item");
						lid = Console.getInt("Line item id: ");
						l = getLine(lid);
						if (l == null) {
							System.out.println("No line item found for id: " + lid);
						} else {
							int rowCount = ldb.deleteLine(l);
							if (rowCount == 1) {
								System.out.println("Line Item has been deleted.\n");
							} else {
								System.out.println("Error deleting line item.");
							}
						}
						break;
					case 99:
						// exit
						break;
					default:
						System.out.println("Error. Invalid command.");
						break;
					}
					continue;
				}
				break;*/
			case "e":
			case "E":
				// exit
				System.out.println("\nGoodbye!");
				break;
			default:
				System.out.println("Error. Invalid command.");
				break;
			}
			break;
		}
	}

	private static String getUserMenu() {
		String str = "USER MENU\n" + "----------\n" + "1  - List Users\n" + "2  - Get User\n" + "3  - Add User\n"
				+ "4  - Update User\n" + "5  - Delete User\n" + "99 - Exit\n" + "Command: ";
		return str;
	}

	private static User getUser(int id) {
		User u = null;
		try {
			u = udb.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	private static String getVendorMenu() {
		String str = "VENDOR MENU\n" + "------------\n" + "1  - List Vendors\n" + "2  - Get Vendor\n"
				+ "3  - Add Vendor\n" + "4  - Update Vendor\n" + "5  - Delete User\n" + "99 - Exit\n" + "Command: ";
		return str;
	}

	private static Vendor getVendor(int id) {
		Vendor v = null;
		try {
			v = vdb.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	private static String getProductMenu() {
		String str = "PRODUCT MENU\n" + "------------\n" + "1  - List Products\n" + "2  - Get Product\n"
				+ "3  - Add Product\n" + "4  - Update Product\n" + "5  - Delete Product\n" + "99 - Exit\n"
				+ "Command: ";
		return str;
	}

	private static Product getProduct(int id) {
		Product p = null;
		try {
			p = pdb.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	private static String getRequestMenu() {
		String str = "REQUEST MENU\n" + "------------\n" + "1  - List Requests\n" + "2  - Get Request\n"
				+ "3  - Add Request\n" + "4  - Update Request\n" + "5  - Delete Request\n" + "99 - Exit\n"
				+ "Command: ";
		return str;
	}

	private static Request getRequest(int id) {
		Request r = null;
		try {
			r = rdb.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	private static String getLineMenu() {
		String str = "LINE ITEM MENU\n" + "------------\n" + "1  - List Line Items\n" + "2  - Get Line Item\n"
				+ "3  - Add Line Item\n" + "4  - Update Line Item\n" + "5  - Delete Line Item\n" + "99 - Exit\n"
				+ "Command: ";
		return str;
	}

	private static Line getLine(int id) {
		Line l = null;
		try {
			l = ldb.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
}