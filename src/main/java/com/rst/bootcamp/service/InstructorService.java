package com.rst.bootcamp.service;

import org.springframework.stereotype.Component;

import com.rst.bootcamp.model.Instructor;

@Component
public interface InstructorService {

	public Instructor retrieveAllInstructors() ;
	public Instructor getInstructorDetails(int id);
	public Instructor getInstructorByName(String instructorName);
	public Instructor saveInstructor(Instructor instructor);
	
}
