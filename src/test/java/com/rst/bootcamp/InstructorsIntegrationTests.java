package com.rst.bootcamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.bootcamp.pojo.BootCampMembers;
import com.rst.bootcamp.repository.InstructorRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class InstructorsIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	InstructorRepository instructorRepo;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8 );

	@Test
	void test_addInstructor(){
		BootCampMembers instructor = new BootCampMembers();
		instructor.setInstructorName("T1");
		instructor.setAdmin("Admin");

		try {
			mockMvc.perform(post("/instructor")
					.contentType(APPLICATION_JSON_UTF8)
					.content(asJsonString(instructor)))
					.andExpect(status().isCreated());
		} catch (Exception e) {
			assert(false);
		}
	}


	@Test
	void test_addSubordinate(){
		try {
			BootCampMembers instructor = new BootCampMembers();
			instructor.setInstructorName("I2");
			

			mockMvc.perform(
					post("/instructor/I41/subordinate")
							.contentType(APPLICATION_JSON_UTF8)
							.content(asJsonString(instructor)))
							.andExpect(status().isCreated());

		} catch (Throwable e) {
			assert(false);
		}
	}

	@Test
	void test_getAllInstructors() {
		try {
			mockMvc.perform(get("/instructors").contentType(APPLICATION_JSON_UTF8))
			.andDo(print())
			.andExpect(content().json("{instructorName:Admin,admin:null,subordinates:[{instructorName:I11,admin:Admin,subordinates:[{instructorName:I21,admin:I11,subordinates:[{instructorName:I41,admin:I21,subordinates:[]}]},{instructorName:I22,admin:I11,subordinates:[{instructorName:I51,admin:I22,subordinates:[]}]},{instructorName:I23,admin:I11,subordinates:[]}]},{instructorName:I12,admin:Admin,subordinates:[{instructorName:I31,admin:I12,subordinates:[]},{instructorName:I32,admin:I12,subordinates:[]}]}]}"))
			.andExpect(status().isOk())
			.andReturn();

		} catch (Throwable e) {
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
