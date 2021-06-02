package com.rst.bootcamp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.repository.InstructorRepository;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {


	@InjectMocks
	private InstructorService business;
	
	@Mock
	private InstructorRepository repository;
	
	@Test
	public void retrieveInstructorsUsingDataService_basic() {
		when(repository.findAll()).thenReturn(
				Arrays.asList(new Instructor(1,"Rohit","A1"),new Instructor(2,"Ronit","A1"))
				); 
		
		List<Instructor> instructors=business.retrieveAllInstructors();
		assertEquals("A1",instructors.get(0).getAdminName());
		assertEquals("Ronit",instructors.get(1).getInstructorName());
		
	}
	
}
