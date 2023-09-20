package com.hr.person.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.person.model.Person;
import com.hr.person.service.PersonService;

@RestController

public class PersonController {

	@Autowired
	private PersonService userService;

	@GetMapping("/users")
	public List<Person> getAllusers() {
		return userService.getAllUser();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Person> getUserById(@PathVariable(value = "id") Long userId) throws Exception {
		Person User = userService.getUserById(userId);
			
		return ResponseEntity.ok().body(User);
	}

	@PostMapping("/users")
	public Person createUser(@RequestBody Person User) {
		return userService.createUser(User);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Person> updateUser(@PathVariable(value = "id") Long userId,
			 @RequestBody Person user) throws Exception {
				final Person updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		userService.deleteUser(userId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
