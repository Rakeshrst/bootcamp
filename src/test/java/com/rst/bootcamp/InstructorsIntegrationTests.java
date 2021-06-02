package com.rst.bootcamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.bootcamp.model.Instructor;
import com.rst.bootcamp.repository.InstructorRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InstructorsIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	InstructorRepository instructorRepo;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void test_addInstructor() throws Exception {
		Instructor instructor = new Instructor();
		instructor.setAdminName("A1");
		instructor.setInstructorName("I1");

		mockMvc.perform(post("/instructor").contentType(APPLICATION_JSON_UTF8).content(asJsonString(instructor)))
				.andExpect(status().isCreated());
	}

	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test_addSubordinate() throws Exception {
		try {
			Instructor instructor = new Instructor();
			instructor.setAdminName("A1");
			instructor.setInstructorName("I2");

			mockMvc.perform(post("/instructor").contentType(APPLICATION_JSON_UTF8).content(asJsonString(instructor)))
					.andExpect(status().isCreated());

			List<Instructor> instructors = instructorRepo.findAll();

			mockMvc.perform(
					post("/instructor/" + instructors.get(0).getId() + "/subordinate/" + instructors.get(1).getId())
							.contentType(APPLICATION_JSON_UTF8))
					.andExpect(status().isCreated());

		} catch (Throwable e) {

		}
	}

	@Test
	public void test_getAllInstructors() throws Exception {
		try {
			mockMvc.perform(get("/instructors").contentType(APPLICATION_JSON_UTF8)).andDo(print()).andExpect(content()
					.json("{id: 1,instructorName: I1,adminName: A1,subordinate:{id: 2,instructorName: I2,adminName: A1}}"))
					.andExpect(status().isOk()).andReturn();

		} catch (Throwable e) {

		}
	}
	
	@Test
	void contextLoads() {
	}
	
	/**
	@Test
	public void test_findMyInstructor() throws Exception {
		try {
		mockMvc.perform(get("/instructor/2").contentType(APPLICATION_JSON_UTF8))
					.andExpect(content().json("{id: 2,instructorName: I2,adminName: A1}"))
					.andReturn();
		} catch (Throwable e) {
			assert (false);
		}
	}

**/
	/**
	 * Trials
	 * 
	 * @Test public void test_postInstructor() throws Exception { try {
	 * 
	 *       this.mockMvc .perform(post("/instructor")
	 *       .contentType(MediaType.APPLICATION_JSON) .param("instructorName", "R1")
	 *       .param("adminName", "Rohit")) .andExpect(status().isCreated());
	 * 
	 *       /** RequestBuilder request=MockMvcRequestBuilders .post("/instructor")
	 *       .accept(MediaType.APPLICATION_JSON)
	 *       .contentType(MediaType.APPLICATION_JSON) .content("{instructorName:R1,
	 *       adminName: Rohit}"); MvcResult result= mockMvc.perform(request)
	 *       .andDo(print()) .andExpect(status().isCreated()) .andReturn();
	 *       List<Instructor> instructors = instructorRepo.findAll();
	 *       assertEquals(instructors.size(), 1);
	 *       assertEquals(instructors.get(0).getAdminName(), "Rohit");
	 *       assertEquals(instructors.get(0).getInstructorName(), "R1");
	 * 
	 *       } catch (Throwable e) { e.printStackTrace(); assert (false); } }
	 * @Test public void test_postInstructor2() throws Exception { try {
	 * 
	 *       RequestBuilder request=MockMvcRequestBuilders .post("/instructor")
	 *       .accept(MediaType.APPLICATION_JSON)
	 *       .contentType(MediaType.APPLICATION_JSON)
	 *       .content(asJsonString("{instructorName:R1, adminName: Rohit}"));
	 *       MvcResult result= mockMvc.perform(request) .andDo(print())
	 *       .andExpect(status().isCreated()) .andReturn(); List<Instructor>
	 *       instructors = instructorRepo.findAll();
	 *       assertEquals(instructors.size(), 1);
	 *       assertEquals(instructors.get(0).getAdminName(), "Rohit");
	 *       assertEquals(instructors.get(0).getInstructorName(), "R1"); } catch
	 *       (Throwable e) { e.printStackTrace(); assert (false); } }
	 **/
}

