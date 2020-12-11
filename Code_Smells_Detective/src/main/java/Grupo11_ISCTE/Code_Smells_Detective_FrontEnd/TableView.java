package Grupo11_ISCTE.Code_Smells_Detective_FrontEnd;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

public class TableView {

	String title;
	String[] columnNames;
	String[][] data;

	public TableView(String title, String[] columnNames, String[][] data) {
		this.title = title;
		this.columnNames = columnNames;
		this.data = data;
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

		JFrame frame = new JFrame(title);

		JTable table = buildTable();

		// adding table to JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		// Frame Size
		frame.setSize(1000, 1000);
		// Frame Visible = true
		frame.setVisible(true);

	}

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
