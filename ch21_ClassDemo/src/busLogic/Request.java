package busLogic;

import java.sql.Date;

public class Request {
	
	private int id;
	private int userID;
	private String description;
	private String justification;
	private Date dateNeeded;
	private String deliveryMode;
	private String status;
	private double total;
	private Date submittedDate;
	private String reasonForRejection;
	
	public Request() {
		id = 0;
		userID = 0;
		description = "";
		justification = "";
		dateNeeded = null;
		deliveryMode = "";
		status = "";
		total = 0.0;
		submittedDate = null;
		reasonForRejection = "";
	}

	public Request(int id, int userID, String description, String justification, Date dateNeeded,
			String deliveryMode, String status, double total, Date submittedDate, String reasonForRejection) {
		this.id = id;
		this.userID = userID;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Date getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(Date dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	
	public String toString() {
		String message = "Purchase Request Information: \n" +
						"Request submitted by: " + userID + "\n" +
						"Description: \n" +
						"Justification: \n" +
						"Date Needed: \n" +
						"Delivery Mode: \n" +
						"Status: \n" +
						"Total: \n" +
						"Date Submitted: \n" +
						"Reason for Rejection, if any: \n";
		return message;
	}

}