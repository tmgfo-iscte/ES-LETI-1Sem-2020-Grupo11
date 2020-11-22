package Grupo11_ISCTE.Code_Smells_Detective;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class ExcelTable {

	String fileToImport;
	
	
	
	public ExcelTable(String fileToImport) {
		this.fileToImport = fileToImport;
	}
	
	public void init() {
		Runnable runGUI = new Runnable() {
			
			@Override
			public void run() {
				buildGUI();
				
			}
		};
		
		SwingUtilities.invokeLater(runGUI);
		
	}
	
	
	private void buildGUI() {
		
		
		
		JFrame frame = new JFrame("Excel File");
		
		JTable table;
		
		FileParser fileParser = new FileParser(fileToImport);
		
		
		
		
		String[][] data = fileParser.dataArray();
	  
	        // Column Names 
	        String[] columnNames = fileParser.columnNamesArray(); 
	  
	        // Initializing the JTable 
	        table = new JTable(data, columnNames); 
	        table.setBounds(30, 40, 200, 300); 
	  
	        // adding it to JScrollPane 
	        JScrollPane sp = new JScrollPane(table); 
	        frame.add(sp); 
	        // Frame Size 
	        frame.setSize(1000, 1000); 
	        // Frame Visible = true 
	        frame.setVisible(true); 
		
		
		
		
	}
	
	
	
}
