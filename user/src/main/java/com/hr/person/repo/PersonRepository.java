package com.hr.person.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
