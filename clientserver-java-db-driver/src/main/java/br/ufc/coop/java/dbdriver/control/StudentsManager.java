package br.ufc.coop.java.dbdriver.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.coop.java.dbdriver.model.Student;
import br.ufc.coop.java.dbdriver.util.DataBaseManager;

public class StudentsManager {
	
	private DataBaseManager dbManager = new DataBaseManager();
	
	public List<Student> getStudents() throws SQLException{
		ResultSet rs = null;
		List<Student> students = new ArrayList<Student>();
		Student student;
		try {
			rs = dbManager.executeQuery("SELECT * FROM student");
			
			while (rs.next()){
				student = new Student();
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setCourse(rs.getString("course"));
				
				students.add(student);
			}
			
			return students;
		} catch (SQLException e) {
			throw e;
		} finally {
			rs.close();
		}
	}

	public long addStudent(Student student) throws SQLException {
		String sqlCommand = "INSERT INTO student (name, email, course) VALUES (\"" + 
				student.getName() + "\", \"" + student.getEmail() + 
				"\", \"" + student.getCourse() + "\" )";
		return dbManager.executeInsert(sqlCommand);
	}

	public void updateStudent(int idToUpdate, Student student) throws SQLException {
		String sqlCommand = "UPDATE student SET name = \"" + 
				student.getName() + "\", email = \"" + student.getEmail() + 
				"\", course = \"" + student.getCourse() + "\" WHERE id = " + idToUpdate;
		dbManager.executeUpdate(sqlCommand);
	}
}
