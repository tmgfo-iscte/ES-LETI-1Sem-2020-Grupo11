package Grupo11_ISCTE.Code_Smells_Detective_FrontEnd;
import Grupo11_ISCTE.Code_Smells_Detective.*;
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
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;

public class FinalGUI {

	private static String[] rules = { "LOC", "CYCLO", "ATFD", "LAA" };
	private static String[] signals = {">", "<="};
	private static  String[] and_or = {"OR", "AND"};
	private static String[] smells = {"Long Method", "Feature Envy"};
	private JFrame frame = new JFrame("Code Smells Detector");
	private JPanel centerPanel;
	private int rulesCounter = 1;
	private ArrayList<Component> ruleComponents = new ArrayList<Component>();
	public String filePathToImport;

	
	public FinalGUI() {
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
		frame.setSize(400, 400);
		frame.setLayout(new BorderLayout());
		
		
		
		// Creating the MenuBar and adding components
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("RULES");
		menuBar.add(menu);
		final JMenuItem addButton = new JMenuItem("Add");		
		menu.add(addButton);
		
		// Adding MenuBar to North of the frame
		frame.add(menuBar,BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4,1));
		
		// Creating panel and elements for first rule
		JPanel firstCenterPanel = new JPanel();
		firstCenterPanel.setLayout(new FlowLayout());
		JComboBox operatorRuleBox = new JComboBox(and_or); // not added to first center panel
		JComboBox firstRuleBox = new JComboBox(rules);
		JComboBox firstSignalBox = new JComboBox(signals); 
		JTextField firstTextFieldBox = new JTextField(5);
		firstCenterPanel.add(firstRuleBox);
		firstCenterPanel.add(firstSignalBox);
		firstCenterPanel.add(firstTextFieldBox);
		centerPanel.add(firstCenterPanel);
		
		// Keeping track of rules components
		ruleComponents.add(operatorRuleBox);
		ruleComponents.add(firstRuleBox);
		ruleComponents.add(firstSignalBox);
		ruleComponents.add(firstTextFieldBox);
		
		// Adding centerPanel to center of the frame
		frame.add(centerPanel, BorderLayout.CENTER);
		
		
		
		// Creating elements for South Panel
		JButton importExcel = new JButton("Import Excel");
		JButton showCodeSmells = new JButton("Show Code Smells");
		JButton showExcel = new JButton("Show Excel");
		JButton showQI = new JButton ("Show Quality Indicators");
		final JComboBox badSmellChooser = new JComboBox(smells);
		
		// Creating South Panel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(importExcel);
		southPanel.add(showCodeSmells);
		southPanel.add(showExcel);
		southPanel.add(showQI);
		southPanel.add(badSmellChooser);
		
		// Adding southPanel to south of the frame
		frame.add(southPanel,BorderLayout.SOUTH);
		
		
		// **********Listeners***********
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
				if(badSmellChooser.getSelectedItem().equals("Long Method")) {
					setDetectionResultsViewForLongMethod().init();	
				}
				if(badSmellChooser.getSelectedItem().equals("Feature Envy")) {
					setDetectionResultsViewForFeatureEnvy().init();
				}
				
			}
		});
		
		showQI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(badSmellChooser.getSelectedItem().equals("Long Method"))
					setQualityIndicatorsViewForLongMethod().init();
				if(badSmellChooser.getSelectedItem().equals("Feature Envy"))
					setQualityIndicatorsViewForFeatureEnvy().init();
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addRule();
				rulesCounter++;
				if(rulesCounter == 4)
					addButton.setEnabled(false);;
			}
		});
		
		
		
		
		// Final frame setup
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	// Adds a new rule when user clicks Menu Button Add Rule
	private void addRule() {
		

		JPanel subCenterPanel = new JPanel();
		subCenterPanel.setLayout(new FlowLayout());
		JComboBox operatorRuleBox = new JComboBox(and_or);
		JComboBox ruleBox = new JComboBox(rules);
		JComboBox signalBox = new JComboBox(signals); 
		JTextField textFieldBox = new JTextField(5);
		subCenterPanel.add(operatorRuleBox);
		subCenterPanel.add(ruleBox);
		subCenterPanel.add(signalBox);
		subCenterPanel.add(textFieldBox);
		centerPanel.add(subCenterPanel);
		centerPanel.updateUI();
		
		// Keeping track of rules components 
		
		ruleComponents.add(operatorRuleBox);
		ruleComponents.add(ruleBox);
		ruleComponents.add(signalBox);
		ruleComponents.add(textFieldBox);
	}
	
	
	private ArrayList<Rule> scanRules() {
		ArrayList<Rule> rules = new ArrayList<Rule>();
		for(int i = 0; i < ruleComponents.size(); i+= 4) {
			JComboBox operatorRuleBox = (JComboBox) ruleComponents.get(i+0);
			JComboBox ruleBox = (JComboBox) ruleComponents.get(i+1);
			JComboBox signalBox = (JComboBox) ruleComponents.get(i+2);
			JTextField textFieldBox = (JTextField) ruleComponents.get(i+3);
			
			boolean isAndOperator = operatorRuleBox.getSelectedItem().equals("AND");
			String metric = (String) ruleBox.getSelectedItem();
			float threshold = Float.parseFloat(textFieldBox.getText());
			boolean isAbove = signalBox.getSelectedItem().equals(">");
			
			Rule rule = new Rule(isAndOperator, metric, threshold, isAbove);
			rules.add(rule);
		}
		
		return rules;
	}
	
	private TableView setDetectionResultsViewForLongMethod() {
		String[] columnNames = { "methodID", "Own Detector", "iPlasma", "PMD" };
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateLongMethodData();

		TableView detectionResultsView = new TableView("Results", columnNames, data);
		
		return detectionResultsView;
	}
	
	private TableView setDetectionResultsViewForFeatureEnvy() {
		String[] columnNames = { "methodID", "Own Detector"};
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateFeatureEnvyData();
		
		TableView detectionResultsView = new TableView("Results", columnNames, data);

		return detectionResultsView;
		
	}
	
	private TableView setQualityIndicatorsViewForLongMethod() {
		
		String[] columnNames = {"Tool", "DCI", "DII", "ADCI", "ADII"};
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateQualityDataForLongMethod();
		
		TableView qualityResultsView = new TableView("Quality Indicators For Long Method", columnNames, data);
		
		return qualityResultsView;
	}
	
	private TableView setQualityIndicatorsViewForFeatureEnvy() {

		String[] columnNames = { "Tool", "DCI", "DII", "ADCI", "ADII" };
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateQualityDataForFeatureEnvy();

		TableView qualityResultsView = new TableView("Quality Indicators For Feature Envy", columnNames, data);

		return qualityResultsView;
	}	

	private TableView setExcelFileView() {
		FileParser fileParser = new FileParser(filePathToImport);
		String[][] data = fileParser.dataArray();
		String[] columnNames = fileParser.columnNamesArray();
		TableView excelFileView = new TableView("Excel File", columnNames, data);
		return excelFileView;
	}
}
