package com.rst.bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.repository.InstructorRepository;

@Component
public class InstructorServiceImpl implements  InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;

	public Instructor retrieveAllInstructors() {
		return instructorRepo.findById(Instructor.ROOT_ID).orElse(null);
	}

	public Instructor saveInstructor(Instructor instructor) {
		instructor = instructorRepo.save(instructor);
		return instructor;
	}

	public Instructor getInstructorDetails(int id) {
		return instructorRepo.findById(id).orElse(null);
	}

	public Instructor getInstructorByName(String instructorName) {
		return instructorRepo.findByInstructorName(instructorName);
	}
}
