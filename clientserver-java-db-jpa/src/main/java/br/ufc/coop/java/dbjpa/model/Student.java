package br.ufc.coop.java.dbjpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String email;
	private String course;
	@ManyToMany(mappedBy="students")
	private List<Discipline> disciplines;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "{\"id\"=" + id + ", \"name\"=" + name + "\", \"email\"=\"" + email + "\", \"course\"=\"" + course + "\"}";
	}
	public List<Discipline> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

}
