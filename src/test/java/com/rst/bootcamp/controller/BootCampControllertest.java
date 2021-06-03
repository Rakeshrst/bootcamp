package com.rst.bootcamp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.pojo.BootCampMembers;
import com.rst.bootcamp.service.InstructorService;

@WebMvcTest(BootCampController.class)
class BootCampControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InstructorService instructorService;

	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),  StandardCharsets.UTF_8);
	
	@Test
	void testFindMyInstructor(){
		Instructor instructor= new Instructor(1,"R1",null);
		Instructor instructor2= new Instructor(2,"R2",instructor);
		Instructor instructor3= new Instructor(3,"R3",instructor);
		instructor.setSubordinates(Arrays.asList(instructor2,instructor3));		
		Instructor instructor4= new Instructor(4,"R4",instructor3);
		instructor3.setSubordinates(Arrays.asList(instructor4));
		when(instructorService.getInstructorByName("R3")).thenReturn(instructor3);
		try {
			mockMvc.perform(get("/instructor/R3").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("{instructorName: R3,admin: R1, subordinates:[{instructorName: R4,admin: R3,subordinates:null}]}"))
			.andReturn();
		} catch (Exception e) {
			assert(false);
			e.printStackTrace();
		}

		
	
	}

	@Test
	void testGetAllInstructors() {
		Instructor instructor= new Instructor(1,"R1",null);
		Instructor instructor2= new Instructor(2,"R2",instructor);
		Instructor instructor3= new Instructor(3,"R3",instructor);
		instructor.setSubordinates(Arrays.asList(instructor2,instructor3));		
		Instructor instructor4= new Instructor(4,"R4",instructor3);
		instructor3.setSubordinates(Arrays.asList(instructor4));
		when(instructorService.retrieveAllInstructors()).thenReturn(instructor);
		
		try {
			mockMvc.perform(get("/instructors/").accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{instructorName:R1,admin:null,subordinates:[{instructorName:R2,admin:R1,subordinates:null},{instructorName:R3,admin:R1,subordinates:[{instructorName:R4,admin:R3,subordinates:null}]}]}"))
			.andReturn();
		} catch (Exception e) {
			assert(false);
			e.printStackTrace();
		}
	}
	
	@Test
	void test_SaveInstructor() {
		Instructor instructor= new Instructor(1,"R1",null);
		Instructor instructor2= new Instructor(2,"R2",instructor);
		Instructor instructor3= new Instructor(3,"R3",instructor);
		instructor.setSubordinates(Arrays.asList(instructor2,instructor3));		
		Instructor instructor4= new Instructor(4,"R4",instructor3);
		instructor3.setSubordinates(Arrays.asList(instructor4));
		Instructor instructor5= new Instructor(5,"R5",instructor4);
		when(instructorService.getInstructorByName("R1")).thenReturn(instructor);
		when(instructorService.getInstructorByName("R2")).thenReturn(instructor2);
		when(instructorService.getInstructorByName("R3")).thenReturn(instructor3);
		when(instructorService.getInstructorByName("R4")).thenReturn(instructor4);
		when(instructorService.getInstructorByName("R5")).thenReturn(instructor5);
		when(instructorService.saveInstructor(Matchers.any(Instructor.class))).thenReturn(instructor5);
		try {
			
			BootCampMembers bootCampMember=new BootCampMembers();
			bootCampMember.setAdmin("R4");
			bootCampMember.setInstructorName("R5");
			mockMvc.perform(post("/instructor")
					.contentType(APPLICATION_JSON_UTF8)
					.content(asJsonString(bootCampMember)))
					.andExpect(status().isCreated())
					.andReturn();

			mockMvc.perform(get("/instructor/R5").accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{instructorName:R5,admin:R4,subordinates:null}"))
			.andReturn();
		
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
			
		}
	}
	

	
	@Test
	void test_addSubordinateByName(){
		Instructor instructor= new Instructor(1,"R1",null);
		Instructor instructor2= new Instructor(2,"R2",instructor);
		Instructor instructor3= new Instructor(3,"R3",instructor);
		instructor.setSubordinates(Arrays.asList(instructor2,instructor3));		
		Instructor instructor4= new Instructor(4,"R4",instructor3);
		instructor3.setSubordinates(Arrays.asList(instructor4));
		Instructor instructor5= new Instructor(5,"R5",instructor4);
		when(instructorService.getInstructorByName("R1")).thenReturn(instructor);
		when(instructorService.getInstructorByName("R2")).thenReturn(instructor2);
		when(instructorService.getInstructorByName("R3")).thenReturn(instructor3);
		when(instructorService.getInstructorByName("R4")).thenReturn(instructor4);
		when(instructorService.getInstructorByName("R5")).thenReturn(instructor5);
		when(instructorService.saveInstructor(Matchers.any(Instructor.class))).thenReturn(instructor5);
		try {
			
			BootCampMembers bootCampMember=new BootCampMembers();
			bootCampMember.setInstructorName("R5");
			mockMvc.perform(post("/instructor/R4/subordinate")
					.contentType(APPLICATION_JSON_UTF8)
					.content(asJsonString(bootCampMember)))
					.andExpect(status().isCreated())
					.andReturn();

			mockMvc.perform(get("/instructor/R5").accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{instructorName:R5,admin:R4,subordinates:null}"))
			.andReturn();
		
		} catch (Exception e) {
			assert(false);
			
		}
	}
	

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	


}
