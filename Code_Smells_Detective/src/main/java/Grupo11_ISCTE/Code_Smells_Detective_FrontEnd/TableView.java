package Grupo11_ISCTE.Code_Smells_Detective_FrontEnd;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

/** Represents a Table View.
* The Table View offers methods to build a Jtable and present it
* @author Marcelo Pereira
* @version 1.0
* @since 1.0
*/

public class TableView {

	/** Represents the title of our main frame,
	* an array of columns with names which type is String
	* and a matrix with data which type is also String.
	*/	
		
	String title;
	String[] columnNames;
	String[][] data;
	
	/** Class Constructor.
	* Creates a instance of the class Table View with the specified title, columns names and data.
	* @param title The main frame title.
	* @param columnNames The column names.
	* @param data The represented data.
	*/

	public TableView(String title, String[] columnNames, String[][] data) {
		this.title = title;
		this.columnNames = columnNames;
		this.data = data;
	}
	/**
	 * This method launches a new thread which builds the GUI.
	 */
	public void init() {
		Runnable runGUI = new Runnable() {
			
			@Override
			public void run() {
				buildGUI();
			}
		};
		
		SwingUtilities.invokeLater(runGUI);
	}
	
	/**
	* This method creates the graphic interface.
	* @return nothing
	*/

	private void buildGUI() {

		/** Creates the main frame giving it a name (title)
		* and builds a table using the method buildTable. 
		*/
		
		JFrame frame = new JFrame(title);
		JTable table = buildTable();

		/** Creates a variable scrollPane with the type JScrollPane 
		* and adds the created table to it.
		* Also sets the main frame with a specified size and visibility.
		*/

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setSize(1000, 1000);
		frame.setVisible(true);

	}
	
	/**
	* This method creates a JTable
	* using @param data and @param columnNames.
	* Also defines background colors for the different truth values 
	* (they can be true or false).
	* @default Color.white If the value it's neither true or false, the color will be white.
	* @return a JTable.
	*/


	private JTable buildTable() {
		JTable table;
		table = new JTable(data, columnNames) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				Object value = getModel().getValueAt(row, col);
				if (value.equals("FALSE") || value.equals("false")) {
			        comp.setBackground(Color.red);
			    } else if (value.equals("TRUE") || value.equals("true")) {
			        comp.setBackground(Color.green);
			    } else {
			       comp.setBackground(Color.white);
			    }
				return comp;
			}
		};
		return table;
	}

}
