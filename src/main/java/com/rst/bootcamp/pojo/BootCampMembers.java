package com.rst.bootcamp.pojo;

public class BootCampMembers {
	private String instructorName;
	private String admin;
	
	public BootCampMembers() {
		super();
	}
	public BootCampMembers(String instructorName, String admin) {
		super();
		this.instructorName = instructorName;
		this.admin = admin;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
