package br.ufc.coop.java.httpspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.coop.java.dbjpa.model.Student;
import br.ufc.coop.java.httpspring.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	@Autowired
	public StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Student addStudent(@RequestBody Student student){
		studentService.addStudent(student);
		return student;
	}
	
}