package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

	private Integer employeeID;
	private String role;
	private ArrayList<String> securityIncidents;

	//	//// CONSTRUCTOR /////////////////////////////////////////////////
	
	// ----- Constructor: takes an ID and a role -------------------------
	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
		this.securityIncidents = new ArrayList<String>();
	}
	

	//	//// ACTIONS /////////////////////////////////////////////////////
	
	//  **** Implement HIPAACompliantUser*********************************
	
	//	----- Assign Pin Method ------------------------------------------
	//	@logic: Pin must be 6 digits or more
	//	@return: True if Pin is 6 digits or more, False if pin is less than 6 digits
	@Override
	public boolean assignPin(int pin) {
		if (pin < 100000) {
			return false;
		}
		this.pin = pin;
		return true;
	}

	//	---- Access Authorized Method -------------------------------------
	//	@logic:	Compares the ids, 
	//				and if they are not a match,
	//					creates an incident report using the  authIncident method,
	//	@Return: true if ids match, false if not.
	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (this.id.equals(confirmedAuthID)) {
			return true;
		}
		this.authIncident();
		return false;
	}
	
	
	// 	**** Implement HIPAACompliantAdmin! *******************************
	
	//	---- Report Security Incidents Method -----------------------------
	//	@return: list of Strings (incidents reported)
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return this.securityIncidents;
	}
	
	
	public void newIncident(String notes) {
		String report = String.format("Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", new Date(),
				this.id, notes);
		securityIncidents.add(report);
	}

	public void authIncident() {
		String report = String.format("Datetime Submitted: %s \n,  ID: %d\n Notes: %s \n", new Date(), this.id,
				"AUTHORIZATION ATTEMPT FAILED FOR THIS USER");
		this.securityIncidents.add(report);
	}
	
	//	//// GETTERS AND SETTERS /////////////////////////////////////////

	public Integer getEmployeeID() {
		return employeeID;
	}

	public String getRole() {
		return role;
	}

	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}
}