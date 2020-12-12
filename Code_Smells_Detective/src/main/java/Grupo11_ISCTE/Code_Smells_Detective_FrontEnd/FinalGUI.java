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

/** Represents the interface of our Code Smells Detector project.
	* The CodeSmellsDetector program implements an application that
	* evaluates the quality of code used in software projects
	* using the detection of code smells.
	* @author Teresa Felício
	* @author Marcelo Pereira
	* @version 3.0
	* @since 1.0
 */

public class FinalGUI {

	/** Represents an array of software metrics whose type is String.
	*/
	
	private static String[] rules = { "LOC", "CYCLO", "ATFD", "LAA" };
	
	/** Represents an array of mathematical symbols whose type is String.
	*/
	
	private static String[] signals = {">", "<="};
	
	/** Represents an array of logical operations whose type is String.
	*/
	
	private static  String[] and_or = {"OR", "AND"};
	
	/** Represents an array of code smells whose type is String.
	*/
	
	private static String[] smells = {"Long Method", "Feature Envy"};
	
	/** Represents the main frame of our GUI.
	*/
	
	private JFrame frame = new JFrame("Code Smells Detector");
	
	/** Represents the central panel.
	*/
	
	private JPanel centerPanel;
	
	/** Represents the rules counter.
	* @default 1
	*/
	
	private int rulesCounter = 1;
	
	/** Represents an ArrayList of components.
	*/
	
	private ArrayList<Component> ruleComponents = new ArrayList<Component>();
	
	/** Represents the name of a file path.
	*/
	
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
	
	/**
	* This method creates the graphic interface.
	* @return nothing.
	*/
	
	public void buildGUI() {

		/** Creates the frame and sets the size and the layout of it.
		*@default EXIT_ON_CLOSE
		*/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(new BorderLayout());
		
		/** Creates the MenuBar and adds components
		*/
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("RULES");
		menuBar.add(menu);
		final JMenuItem addButton = new JMenuItem("Add");		
		menu.add(addButton);
		
		/** Adds the MenuBar to the north of the main frame.
		*/
		
		frame.add(menuBar,BorderLayout.NORTH);
		
		/** Creates the center panel and sets the layout of it.
		*/
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4,1));
		
		/** Creates one more panel inside the center panel, 
		* define it's layout 
		* and also creates the elements for the first rule.
		* @default won't include the variable operatorRuleBox
		* variable firstTextFieldBox by @default 5
		*/
		
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
		
		/** Keep the track of rules components.
		* Adds the following components to the ArrayList.
		*/
		
		ruleComponents.add(operatorRuleBox);
		ruleComponents.add(firstRuleBox);
		ruleComponents.add(firstSignalBox);
		ruleComponents.add(firstTextFieldBox);
		
		/** Adds the center panel to the center of the main frame.
		*/
		
		frame.add(centerPanel, BorderLayout.CENTER);
		
		/** Creates the elements for the South Panel
		*/
		
		JButton importExcel = new JButton("Import Excel");
		JButton showCodeSmells = new JButton("Show Code Smells");
		JButton showExcel = new JButton("Show Excel");
		JButton showQI = new JButton ("Show Quality Indicators");
		final JComboBox badSmellChooser = new JComboBox(smells);
		
		/** Creates the South Panel, sets the layout of it
		* and adds the elements that were created previously.
		*/
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(importExcel);
		southPanel.add(showCodeSmells);
		southPanel.add(showExcel);
		southPanel.add(showQI);
		southPanel.add(badSmellChooser);
		
		/** Adds the South Panel to the south part of the main frame.
		*/
		
		frame.add(southPanel,BorderLayout.SOUTH);
		
		/** Listeners 
		*/
		
		/** Adds a listener to the button importExcel.
		* When clicking the button, the app will show the files the user has
		* ordered by directories, and the user should select the one he pretends.
		* !!not implemented!! @default should force the user to only choose an Excel File
		*/
		
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
		
		/** Adds a listener to the button showExcel.
		* When clicking the button, the app will open and show the Excel file 
		* that the user chose previously.
		* !!not implemented!! @default should force the button to only open an Excel File
		*/
		
		showExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setExcelFileView().init();

			}
		});
		
		/** Adds a listener to the button showCodeSmells.
		* When clicking the button, the app will show a table of code smells.
		*/
		
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
		
		/** Adds a listener to the button showQI.
		* When clicking the button, the app will show the quality indicators.
		*/
		
		showQI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(badSmellChooser.getSelectedItem().equals("Long Method"))
					setQualityIndicatorsViewForLongMethod().init();
				if(badSmellChooser.getSelectedItem().equals("Feature Envy"))
					setQualityIndicatorsViewForFeatureEnvy().init();
			}
		});
		
		/** Adds a listener to the button addButton.
		* When clicking the button, the app will add a new rule 
		* underneath the previous rule.
		* @default one rule already it's created
		* When 4 rules are created, the user can't add more rules.
		* !!not implemented!! variable rulesCounterMAX by @default 4
		*/
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addRule();
				rulesCounter++;
				if(rulesCounter == 4)
					addButton.setEnabled(false);;
			}
		});
		
		/** Adjusts the main frame according to their components
		* and sets the visibility of it.
		*@default EXIT_ON_CLOSE
		*/
	
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	* This method adds a new rule, when the user clicks "Menu Button" -> "Add Rule".
	* @return nothing.
	*/
	
	private void addRule() {
		
		/**
		 * Creates a sub center panel and define it's layout.
		 * Also creates new components and adds them to the sub center panel.
		 * The sub center panel will be added to the center panel
		 * and due to that, the center panel will be updated.
		 */

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
		
		/** Keep the track of rules components.
		* Adds the following components to the ArrayList.
		*/
		
		ruleComponents.add(operatorRuleBox);
		ruleComponents.add(ruleBox);
		ruleComponents.add(signalBox);
		ruleComponents.add(textFieldBox);
	}

	/**
	* This method prints the rules.
	* @return an ArrayList<Rule>
	*/
	
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
	
	/**
	* This method sets the detection results for the code smell "Long Method".
	* @return a TableView.
	*/
	
	private TableView setDetectionResultsViewForLongMethod() {
		String[] columnNames = { "methodID", "Own Detector", "iPlasma", "PMD" };
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateLongMethodData();

		TableView detectionResultsView = new TableView("Results", columnNames, data);
		
		return detectionResultsView;
	}
	
	/**
	* This method sets the detection results for the code smell "Feature Envy".
	* @return a TableView.
	*/
	
	private TableView setDetectionResultsViewForFeatureEnvy() {
		String[] columnNames = { "methodID", "Own Detector"};
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateFeatureEnvyData();
		
		TableView detectionResultsView = new TableView("Results", columnNames, data);

		return detectionResultsView;
		
	}
	
	/**
	* This method sets the quality indicator for the code smell "Long Method".
	* @return a TableView.
	*/
	
	private TableView setQualityIndicatorsViewForLongMethod() {
		
		String[] columnNames = {"Tool", "DCI", "DII", "ADCI", "ADII"};
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateQualityDataForLongMethod();
		
		TableView qualityResultsView = new TableView("Quality Indicators For Long Method", columnNames, data);
		
		return qualityResultsView;
	}
	
	/**
	* This method sets the quality indicator for the code smell "Feature Envy".
	* @return a TableView.
	*/
	
	private TableView setQualityIndicatorsViewForFeatureEnvy() {

		String[] columnNames = { "Tool", "DCI", "DII", "ADCI", "ADII" };
		ArrayList<Rule> rules = scanRules();
		Detector detector = new Detector(filePathToImport, rules);
		String[][] data = detector.generateQualityDataForFeatureEnvy();

		TableView qualityResultsView = new TableView("Quality Indicators For Feature Envy", columnNames, data);

		return qualityResultsView;
	}	

	/**
	* This method sets the Excel File view.
	* @return a TableView.
	*/
	
	private TableView setExcelFileView() {
		FileParser fileParser = new FileParser(filePathToImport);
		String[][] data = fileParser.dataArray();
		String[] columnNames = fileParser.columnNamesArray();
		TableView excelFileView = new TableView("Excel File", columnNames, data);
		return excelFileView;
	}
}
