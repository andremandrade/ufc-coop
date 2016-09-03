package br.ufc.coop.java.httpspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.coop.java.httpspring.model.Student;
import br.ufc.coop.java.httpspring.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	@Autowired
	public StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Student> getStudents(@RequestParam (defaultValue="") String email){
//		return studentService.getStudents();
		System.out.println("Param:" + email);
		return studentService.getStudentsByEmail(email);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Student addStudent(@RequestBody Student student){
		studentService.addStudent(student);
		return student;
	}
	
}