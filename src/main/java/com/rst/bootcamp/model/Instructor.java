package com.rst.bootcamp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Instructor {

	
	public static final int ROOT_ID = 1;
	@Id
	@GeneratedValue
	@JsonIgnore
	private int id;
	
	@Column(unique=true)
	private String instructorName;


	  @JsonIgnore
	  @ManyToOne(cascade = { CascadeType.PERSIST})
	  private Instructor parent;
	  
	  @Transient
	  private String admin;
	  
	  @OneToMany(mappedBy="parent", cascade = { CascadeType.REMOVE, CascadeType.PERSIST} )
	  private List<Instructor> subordinates;

	 
	
	  
	public Instructor() {
	    }
	
	  public Instructor(int id, String instructorName) {
		super();
		this.id = id;
		this.instructorName = instructorName;
	}


	public Instructor(int id, String instructorName, Instructor parent) {
		super();
		this.id = id;
		this.instructorName = instructorName;
		this.parent = parent;
	}

	public Instructor(int id, String instructorName, Instructor parent, List<Instructor> subordinates) {
		super();
		this.id = id;
		this.instructorName = instructorName;
		this.parent = parent;
		this.subordinates = subordinates;
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

	public Instructor getParent() {
		return parent;
	}

	public void setParent(Instructor parent) {
		this.parent = parent;
	}

	public List<Instructor> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Instructor> subordinates) {
		this.subordinates = subordinates;
	}
	

	public String getAdmin() {
		if(getParent()!=null) {
		return getParent().getInstructorName();
		}
		else {
	return null;
		}
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", instructorName=" + instructorName + ", parent=" + parent + ", admin="
				+ admin + ", subordinates=" + subordinates + "]";
	}



	  
}