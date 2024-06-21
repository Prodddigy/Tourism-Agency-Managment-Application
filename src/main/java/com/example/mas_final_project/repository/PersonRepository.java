package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
