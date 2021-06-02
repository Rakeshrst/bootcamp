package com.rst.bootcamp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Instructor {

	@Id
	@GeneratedValue
	private int id;

	private String instructorName;

	private String adminName;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	@JoinTable(name = "instructor_hierarchy", joinColumns = {
			@JoinColumn(name = "instructor_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "subordinate_id", referencedColumnName = "id") })
	private Instructor subordinate;

	public Instructor() {
	}

	public Instructor(int id, String instructorName, String adminName) {
		super();
		this.id = id;
		this.instructorName = instructorName;
		this.adminName = adminName;
	}

	public Instructor(String instructorName, String adminName) {
		this.instructorName = instructorName;
		this.adminName = adminName;
	}
	public Instructor(int id, String instructorName, String adminName, Instructor subordinate) {
		super();
		this.id = id;
		this.instructorName = instructorName;
		this.adminName = adminName;
		this.subordinate = subordinate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Instructor getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(Instructor subordinate) {
		this.subordinate = subordinate;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", instructorName=" + instructorName + ", adminName=" + adminName
				+ ", subordinate=" + subordinate + "]";
	}

}
