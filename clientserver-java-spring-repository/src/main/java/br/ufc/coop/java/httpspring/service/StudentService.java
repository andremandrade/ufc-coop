package br.ufc.coop.java.httpspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.coop.java.httpspring.model.Student;
import br.ufc.coop.java.httpspring.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Iterable<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void addStudent(Student student) {
		studentRepository.save(student);
	}

	public Iterable<Student> getStudentsByEmail(String email) {
		if (email.isEmpty())
			return getStudents();
		return studentRepository.findByEmail(email);
	}

}
