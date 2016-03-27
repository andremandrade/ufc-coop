package br.ufc.coop.java.dbjpa.util;

import java.util.Scanner;

public class InputManager {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static int readInt(){
		return scanner.nextInt();
	}

	public static String readText(){
		return scanner.nextLine();
	}
}
