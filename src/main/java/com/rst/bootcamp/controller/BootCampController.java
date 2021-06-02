package com.rst.bootcamp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.service.InstructorService;

@RestController
public class BootCampController {

	@Autowired
	private InstructorService instructorService;

	//Get Instructor Data using Id

	@GetMapping("/instructor/{id}")
	public Instructor findMyInstructor(@PathVariable int id) {
		
		return instructorService.getInstructorDetails(id);
	}
	
	//Get All the instructors
	
	@GetMapping("/instructors")
	public List<Instructor> getAllInstructors() {
		
		return instructorService.retrieveAllInstructors();
	}

	@PostMapping("/instructor")
	public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor) {
		
		instructor=instructorService.saveInstructor(instructor);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(instructor.getId())
                .toUri();

		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/instructor/{id}/subordinate")
	public ResponseEntity<Void> addSubordinate(@PathVariable int id,@RequestBody Instructor subordinate) {

		subordinate=instructorService.saveInstructor(subordinate);
		
		Instructor instructor= instructorService.getInstructorDetails(id);
	
		instructorService.addSubordinate(instructor, subordinate);
		 instructor= instructorService.getInstructorDetails(id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(instructor.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/instructor/{id}/subordinate/{subordinate_id}")
	public ResponseEntity<Void> addSubordinate(@PathVariable int id,@PathVariable int subordinate_id) {

		Instructor instructor= instructorService.getInstructorDetails(id);
		Instructor subordinate= instructorService.getInstructorDetails(subordinate_id);
		instructorService.addSubordinate(instructor, subordinate);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(instructor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
