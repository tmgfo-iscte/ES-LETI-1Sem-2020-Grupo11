package Grupo11_ISCTE.Code_Smells_Detective;

import Grupo11_ISCTE.Code_Smells_Detective_FrontEnd.FinalGUI;

/**
 * Main point of entry in the program
 * 
 * @author Marcelo Pereira
 *
 */
public class App {
	/**
	 * This method launches the program
	 * 
	 * @param args used to launch the program
	 */
	public static void main(String[] args) {

		FinalGUI gui = new FinalGUI();
		gui.init();
	}
}
