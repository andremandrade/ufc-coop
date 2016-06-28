package br.ufc.coop.java.dbdriver.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.coop.java.dbdriver.model.Student;
import br.ufc.coop.java.dbdriver.util.DataBaseManager;

public class StudentsManager {
	
	public StudentsManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		DataBaseManager.loadDriver();
	}
	
	public List<Student> getStudents() throws SQLException{
		Connection conn = DataBaseManager.getConnection();
		ResultSet rs = null;
		try {
			List<Student> students = new ArrayList<Student>();
			Student student;
			
			conn = DataBaseManager.getConnection();
			
			rs = DataBaseManager.executeQuery("SELECT * FROM student", conn);
			
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
			if(rs != null) rs.close();
			if(conn != null) conn.close();
		}
	}

	public long addStudent(Student student) throws SQLException {
		String sqlCommand = "INSERT INTO student (name, email, course) VALUES (\"" + 
				student.getName() + "\", \"" + student.getEmail() + 
				"\", \"" + student.getCourse() + "\" )";
		System.out.println(sqlCommand);
		return DataBaseManager.executeInsert(sqlCommand);
	}

	public void updateStudent(int idToUpdate, Student student) throws SQLException {
		String sqlCommand = "UPDATE student SET name = \"" + 
				student.getName() + "\", email = \"" + student.getEmail() + 
				"\", course = \"" + student.getCourse() + "\" WHERE id = " + idToUpdate;
		System.out.println(sqlCommand);
		DataBaseManager.executeUpdate(sqlCommand);
	}
}
