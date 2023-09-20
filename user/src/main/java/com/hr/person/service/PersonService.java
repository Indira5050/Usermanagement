package com.hr.person.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.person.model.Person;
import com.hr.person.repo.PersonRepository;

@Service
@Transactional
public class PersonService {
	
	@Autowired
	private PersonRepository userRepository;
	
	

	public Person createUser(Person user) {
		return userRepository.save(user);
	}

	
	public Person updateUser(Person user) throws Exception {
		Optional<Person> userDb = this.userRepository.findById(user.getId());
		
		if(userDb.isPresent()) {
			Person userUpdate = userDb.get();
			userUpdate.setId(user.getId());
			userUpdate.setFirstName(user.getFirstName());
			userUpdate.setLastName(user.getLastName());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPhoneNumber(user.getPhoneNumber());
			userUpdate.setStatus(user.getStatus());
			
			userRepository.save(userUpdate);
			return userUpdate;
		}else {
			throw new Exception("Record not found with id : " + user.getId());
		}		
	}

	
	public List<Person> getAllUser() {
		return this.userRepository.findAll();
	}

	
	public Person getUserById(long userId) throws Exception {
		
		Optional<Person> userDb = this.userRepository.findById(userId);
		
		if(userDb.isPresent()) {
			return userDb.get();
		}else {
			throw new Exception("Record not found with id : " + userId);
		}
	}


	public void deleteUser(long userId) throws Exception {
		Optional<Person> userDb = this.userRepository.findById(userId);
		
		if(userDb.isPresent()) {
			this.userRepository.delete(userDb.get());
		}else {
			throw new Exception("Record not found with id : " + userId);
		}
		
	}

}
