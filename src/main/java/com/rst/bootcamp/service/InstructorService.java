package com.rst.bootcamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.repository.InstructorRepository;

@Component
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepo;
	
	public List<Instructor> retrieveAllInstructors(){
		
		return instructorRepo.findAll();
	}
	
	public Instructor saveInstructor(Instructor instructor){
		
		instructor= instructorRepo.save(instructor);
		
		return instructor;
	}
	
	public Instructor getInstructorDetails(int id){
		
		Instructor instructor= instructorRepo.findById(id).get();
		
		return instructor;
	}
	
	
	
	public void addSubordinate(Instructor instructor,Instructor subordinate) {
		instructor.setSubordinate(subordinate);
		instructorRepo.save(instructor);
	}
	
}
