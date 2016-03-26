package br.ufc.coop.java.dbdriver;

import br.ufc.coop.java.dbdriver.util.DataBaseManager;
import br.ufc.coop.java.dbdriver.view.Menu;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			
			DataBaseManager.loadDriver();
			System.out.println("================" + 
					"\n-- School App --" + 
					"\n================");
			
			while(true){
				Menu.showMain();
			}
		
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
