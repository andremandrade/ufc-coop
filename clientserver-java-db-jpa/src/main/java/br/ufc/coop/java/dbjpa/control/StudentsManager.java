package br.ufc.coop.java.dbjpa.control;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufc.coop.java.dbjpa.model.Student;
import br.ufc.coop.java.dbjpa.util.DatabaseManager;

public class StudentsManager {

	private DatabaseManager databaseManager = new DatabaseManager();
	private EntityManager entityManager;
	
	public StudentsManager() throws Exception {
		databaseManager.setUp();
		entityManager = databaseManager.getEntityManager();
	}

	public List<Student> getStudents() {
		entityManager.getTransaction().begin();
		List<Student> result = entityManager.createQuery("select s from Student s", Student.class).getResultList();
		entityManager.getTransaction().commit();
		return result;
	}
	
	public long addStudent(Student student){
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return student.getId();
	}
	
	public void updateStudent(int idToUpdate, Student student){
		entityManager.getTransaction().begin();
		student.setId(idToUpdate);
		entityManager.merge(student);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
}
