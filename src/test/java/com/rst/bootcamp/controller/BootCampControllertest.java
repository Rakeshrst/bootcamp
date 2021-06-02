package com.rst.bootcamp.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.service.InstructorService;

@WebMvcTest(BootCampController.class)
class BootCampControllertest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InstructorService instructorService;

	
	
	@Test
	void testFindMyInstructor() throws Exception{
		when(instructorService.getInstructorDetails(anyInt())).thenReturn(
				new Instructor(1,"Rohit","A1"));
		mockMvc.perform(get("/instructor/5").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json("{id: 1,instructorName: Rohit,adminName: A1}"))
		.andReturn();

		
	
	}

	@Test
	void testGetAllInstructors() throws Exception {
		when(instructorService.retrieveAllInstructors()).thenReturn(Arrays.asList(
				new Instructor(1,"Rohit","A1"),new Instructor(2,"I2","A1")));
		
		mockMvc.perform(get("/instructors").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json("[{id: 1,instructorName: Rohit,adminName: A1},{id: 2,instructorName: I2,adminName: A1}]"))
		.andReturn();
	}


}
