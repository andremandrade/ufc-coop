package br.ufc.coop.java.dbjpa.view;

import br.ufc.coop.java.dbjpa.control.StudentsManager;
import br.ufc.coop.java.dbjpa.model.Student;
import br.ufc.coop.java.dbjpa.util.InputManager;


public class Menu {

	public static StudentsManager studentsManager;

	public static void showMain() {
		while (true) {
			try {
				studentsManager = new StudentsManager();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
	
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
	}

	private static void showList() {
		System.out.println("\n|     ID     |      NAME     |     EMAIL    |    COURSE    |");
			for (Student student : studentsManager.getStudents()) {
				System.out.println("| " + student.getId() + " | " 
						+ student.getName() + " | " 
						+ student.getEmail() + " | " 
						+ student.getCourse() + " |");
			}
	}
	
	private static void showAdd() {
		Student student = new Student();
		System.out.println("\n====== New Student =====");
		showStudentForm(student);
		
		long studentId = studentsManager.addStudent(student);
		System.out.println("\nNew student id: " + studentId);
	}

	private static void showUpdate() {
		Student student = new Student();
		System.out.println("\n====== Update Student =====");
		System.out.println("Choose the student ID: ");
		int idToUpdate = InputManager.readInt();
		System.out.println("- Input new values bellow -");
		showStudentForm(student);

		studentsManager.updateStudent(idToUpdate, student);
		System.out.println("\nStudent " + idToUpdate + " was updated.");
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
