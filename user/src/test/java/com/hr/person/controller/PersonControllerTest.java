package com.hr.person.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.person.model.Person;
import com.hr.person.service.PersonService;



@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
	
	@InjectMocks
	PersonController personController;
	
	@Mock
	PersonService personService;
	
	
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

	}

	@Test
	void testgetAllPersons() throws Exception {

		
		when(personService.getAllUser()).thenReturn(getAllPersonsResponse());

		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		

	}
	
	
	@Test
	void testgetPerson() throws Exception {
		
		when(personService.getUserById(Mockito.anyLong())).thenReturn(getPersonResponse());

		mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		

	}
	
	
	@Test
	void testUpdatePerson() throws Exception {
		
		when(personService.updateUser(Mockito.any())).thenReturn(getPersonResponse());

		mockMvc.perform(put("/users/1").content(getPersonStringJson()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		

	}
	
	
	@Test
	void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/users/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		

	}
	
	private List<Person> getAllPersonsResponse(){
		List<Person> persons = new ArrayList<>();
		Person person =getPersonResponse();
		persons.add(person);
		return persons;
	}
	
	private Person getPersonResponse(){
		
		Person person = new Person();
		person.setEmail("admin@gmail.com");
		person.setFirstName("adminFirst");
		person.setLastName("adminLast");
		person.setId(1);
		person.setPhoneNumber(BigInteger.TEN);
		person.setStatus("single");
		
		return person;
	}
	
	private String getPersonStringJson() throws JsonProcessingException {
		Person person =getPersonResponse();
		return (new ObjectMapper()).writeValueAsString(person);
		
	}
}
