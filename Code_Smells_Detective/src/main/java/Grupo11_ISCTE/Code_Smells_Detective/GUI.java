package Grupo11_ISCTE.Code_Smells_Detective;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

public class GUI {

	private static String[] rules = { "LOC", "CYCLO", "ATFD", "LAA" };
	private static String[] signals = {">=", "<="};
	private static  String[] and_or = { "OR", "AND" };
	private ArrayList<Component> list = new ArrayList<>();
	private static JComboBox rule = new JComboBox(rules);
	private static JComboBox signal = new JComboBox(signals);
	private static JComboBox or_and = new JComboBox(and_or);
	private static JTextField value = new JTextField(5);
	private JFrame frame = new JFrame("Code Smells Detector");
	private int nAt = 1;
	ArrayList<Component> updatedList = new ArrayList<Component>();
	public String filePathToImport;

	public GUI() {
		filePathToImport = "";
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

	public void buildGUI() {


		// Creating the Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);

		// Creating the MenuBar and adding components
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("RULES");
		mb.add(m1);
		JMenuItem add = new JMenuItem("Add");
		if(nAt == 4)
			add.setVisible(false);
		JMenuItem reset = new JMenuItem("Reset");
		m1.add(add);
		m1.add(reset);

		// Creating the panel at bottom and adding components
		JPanel panel = new JPanel(); // the panel is not visible in output
		JButton importExcel = new JButton("Import Excel");
		JButton showCodeSmells = new JButton("Show Code Smells");
		JButton showExcel = new JButton("Show Excel");
		JButton showQI = new JButton ("Show Quality Indicators");
		// Components Added using Flow Layout
		panel.add(importExcel); 
		panel.add(showCodeSmells);
		panel.add(showExcel);
		panel.add(showQI);

		// Icon at the Center
		list.add(rule);
		list.add(signal);
		list.add(value);
		JPanel mid = createMid();
		

		importExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					filePathToImport = selectedFile.getAbsolutePath();
				}
			}
		});

		showExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setExcelFileView().init();

			}
		});

		showCodeSmells.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDetectionResultsView().init();
			}
		});
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				nAt = 1;
				updatedList = new ArrayList<Component>();
				value.setText("");
				redoGUI();		
			}
		});
		
		//add exception in TextField
		/*
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Integer.parseInt(value.getText() );
					}
					 
					catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The value must be a numeric value. " );
					}
			}
		});
		
		*/

		// Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.getContentPane().add(BorderLayout.CENTER, mid);
		frame.setVisible(true);
		frame.pack();
	}

	private TableView setDetectionResultsView() {
		String[] columnNames = { "methodID", "Own Detector", "iPlasma", "PMD" };

		Rule locRule = new Rule(false, "LOC", 400);

		Rule cycloRule = new Rule(true, "CYCLO", 50);

		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(locRule);
		rules.add(cycloRule);
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateData();

		TableView detectionResultsView = new TableView("Results", columnNames, data);

		return detectionResultsView;
	}

	private TableView setExcelFileView() {
		FileParser fileParser = new FileParser(filePathToImport);
		String[][] data = fileParser.dataArray();
		String[] columnNames = fileParser.columnNamesArray();
		TableView excelFileView = new TableView("Excel File", columnNames, data);
		return excelFileView;
	}
	
	private void redoGUI() {
		frame.removeAll();
		frame.setVisible(false);
		JFrame newFrame = new JFrame("Code Smells Detector");
		setFrame(newFrame);
		buildGUI();	
	}

	private JPanel createMid() {
		int i = 1;
		JPanel mid = new JPanel();
		mid.setLayout(new GridLayout(4,1));
		
		JPanel midMid = new JPanel();
		midMid.setLayout(new FlowLayout());
		for (Component o : getList()) {
			midMid.add(o);
		}
		mid.add(midMid);
		while(i!= nAt && nAt != 5) {
			
			ArrayList<Component> lista = new ArrayList<Component>();

			if(!updatedList.isEmpty()&& updatedList.size()<12) {
				if(updatedList.size()>=4 && updatedList.size()<=12) {
				for(int j=0; j<4;j++) {
					lista.add(updatedList.get(j));
					
				}
				JPanel jp = createLayout(lista);
				mid.add(jp);
				newList();
				
				}
				if(updatedList.size()>=8 && updatedList.size()<=12) {
					lista = new ArrayList<Component>();
				for(int j=4; j<8;j++) {
					lista.add(updatedList.get(j));
				
				}
				JPanel jp = createLayout(lista);
		
				mid.add(jp);
		
				}
				if(updatedList.size()==12) {
				
					lista = new ArrayList<Component>();
				for(int j=8; j<12;j++) {
					lista.add(updatedList.get(j));
				
				}
				JPanel jp = createLayout(lista);
				
				mid.add(jp);
				
				}
			
				i++;
		}else {
			lista = newList();
			JPanel jp = createLayout(lista);
			mid.add(jp);
			}
			return mid;
		}
		
		return mid;

	}
	private ArrayList<Component> newList(){
		ArrayList<Component> updatedList = new ArrayList<Component>();
		updatedList.add(new JComboBox(and_or));
		this.updatedList.add(updatedList.get(0));
		updatedList.add(new JComboBox(rules));
		this.updatedList.add(updatedList.get(1));
		updatedList.add(new JComboBox(signals));
		this.updatedList.add(updatedList.get(2));
		updatedList.add(new JTextField(5));
		this.updatedList.add(updatedList.get(3));
		return updatedList;
	}
	private JPanel createLayout(ArrayList<Component> lista) {

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new FlowLayout());
		for (Component o : lista) 
			newPanel.add(o);
		return newPanel;
	}

	private void update() {
		nAt++;
		redoGUI();
	}

	public ArrayList<Component> getList() {
		return list;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
