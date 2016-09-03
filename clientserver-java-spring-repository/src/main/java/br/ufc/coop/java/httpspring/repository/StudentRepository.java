package br.ufc.coop.java.httpspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufc.coop.java.httpspring.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	
	List<Student> findByEmail(String email);

}
