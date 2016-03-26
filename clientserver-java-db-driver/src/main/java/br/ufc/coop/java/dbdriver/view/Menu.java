package br.ufc.coop.java.dbdriver.view;

import java.sql.SQLException;

import br.ufc.coop.java.dbdriver.control.StudentsManager;
import br.ufc.coop.java.dbdriver.model.Student;
import br.ufc.coop.java.dbdriver.util.InputManager;

public class Menu {

	public static StudentsManager studentsManager = new StudentsManager();

	public static void showMain() {

		System.out.println("Choose an option bellow:");
		System.out.println("1. List students");
		System.out.println("2. Add student");
		System.out.println("3. Update Student");
		System.out.println("4. Remove Student");

		int choose = InputManager.readInt();

		switch (choose) {
		case 1:
			showList();
			break;
		case 2:
			showAdd();
			break;
		case 3:
			showUpdate();
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}
	}

	private static void showList() {
		System.out.println("\n|     ID     |      NAME     |     EMAIL    |    COURSE    |");
		try {
			for (Student student : studentsManager.getStudents()) {
				System.out.println("| " + student.getId() + " | " 
						+ student.getName() + " | " 
						+ student.getEmail() + " | " 
						+ student.getCourse() + " |");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void showAdd() {
		Student student = new Student();
		System.out.println("\n====== New Student =====");
		showStudentForm(student);
		
		try {
			
			long studentId = studentsManager.addStudent(student);
			System.out.println("\nNew student id: " + studentId);
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		}		
	}

	private static void showUpdate() {
		Student student = new Student();
		System.out.println("\n====== Update Student =====");
		System.out.println("Choose the student ID: ");
		int idToUpdate = InputManager.readInt();
		System.out.println("- Input new values bellow -");
		showStudentForm(student);
		
		try {
			
			studentsManager.updateStudent(idToUpdate, student);
			System.out.println("\nStudent " + idToUpdate + " was updated.");
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	private static void showStudentForm(Student student) {
		InputManager.readText();
		System.out.println("Name: ");
		student.setName(InputManager.readText());
		System.out.println("Email: ");
		student.setEmail(InputManager.readText());
		System.out.println("Course: ");
		student.setCourse(InputManager.readText());
	}
}
