package com.rst.bootcamp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.repository.InstructorRepository;


@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {


	@InjectMocks
	private InstructorServiceImpl business;
	
	@Mock
	private InstructorRepository repository;

	
	@Test
	void retrieveInstructorsUsingDataService_basic() {
		Optional<Instructor> instructor= Optional.of(new Instructor(1,"Admin",null));
		Instructor instructor2= new Instructor(2,"I1",instructor.get());
		Instructor instructor3= new Instructor(3,"I2",instructor.get());
		instructor.get().setSubordinates(Arrays.asList(instructor2,instructor3));
		when(repository.findById(anyInt())).thenReturn(instructor);
		
		Instructor instructorTest=business.retrieveAllInstructors();
		assertEquals("Admin",instructorTest.getInstructorName());
		
	}
	
}
