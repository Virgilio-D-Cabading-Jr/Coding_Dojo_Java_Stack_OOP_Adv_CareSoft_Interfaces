package com.caresoft.clinicapp;
import java.util.ArrayList;

public class Physician extends User implements HIPAACompliantUser {
	private ArrayList<String> patientNotes;

	//	//// CONSTRUCTOR //////////////////////////////////////////
	
	public Physician(Integer id) {
		super(id);
	}
	
	//	//// ACTION METHODS ///////////////////////////////////////
			
	public void newPatientNotes(String notes, String patientName, Date date) {
		String report;
		report = String.format("Datetime Submitted: %s \n", date);
		report += String.format("Reported By ID: %s\n", this.id);
		report += String.format("Patient Name: %s\n", patientName);
		report += String.format("Notes: %s \n", notes);
		this.patientNotes.add(report);
	}

	@Override
	public boolean assignPin(int pin) {
		if ((pin < 1000) || (pin > 9999)) {
			return false										// Pin must be exactly 4 digits, returns false if not.
		}
		this.pin = pin;
		return true;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		// TODO Auto-generated method stub
		return false;
	}

	//	//// GET AND SET //////////////////////////////////////////
	
	public ArrayList<String> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(ArrayList<String> patientNotes) {
		this.patientNotes = patientNotes;
	}

}

