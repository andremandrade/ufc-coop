package br.ufc.coop.java.httpspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.coop.java.dbjpa.control.StudentsManager;
import br.ufc.coop.java.dbjpa.model.Student;

@Service
public class StudentService {
	
	private StudentsManager studentsManager;
	
	public StudentService() {
		try {
			studentsManager = new StudentsManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getStudents(){
		return studentsManager.getStudents();
	}

	public void addStudent(Student student) {
		studentsManager.addStudent(student);
	}

}
