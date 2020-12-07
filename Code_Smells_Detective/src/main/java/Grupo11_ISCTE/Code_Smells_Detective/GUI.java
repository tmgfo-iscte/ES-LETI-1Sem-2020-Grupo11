package Grupo11_ISCTE.Code_Smells_Detective;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

public class GUI {

	private static String[] quality_evaluation = { "Textual", "Tabular", "Gráfica" };
	private static String[] rules = { "LOC", "CYCLO", "ATFD", "LAA" };
	private static String[] signals = {">=", "<="};
	private static  String[] and_or = { "OR", "AND" };
	private ArrayList<Component> lista = new ArrayList<>();
	private static JComboBox rule = new JComboBox(rules);
	private static JComboBox signal = new JComboBox(signals);
	private static JComboBox or_and = new JComboBox(and_or);
	private static JTextField value = new JTextField(5);
	private JFrame frame = new JFrame("Code Smells Detector");
	private int nAt = 1;
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
		JComboBox quality = new JComboBox(quality_evaluation);
		JButton send = new JButton("Check");
		JButton showExcel = new JButton("Show Excel");
		quality.setSelectedIndex(2);
		panel.add(importExcel); // Components Added using Flow Layout
		panel.add(quality);
		panel.add(send);
		panel.add(showExcel);

		// Icon at the Center
		lista.add(rule);
		lista.add(signal);
		lista.add(value);
		JPanel mid = criarCentro();
		

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

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDetectionResultsView().init();
			}
		});
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				nAt = 1;
				refazGui();		
			}
		});
		
		//adicionar uma exceção no TextField
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
		// ajusta a janela consoante o que está no centro
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
	
	private void refazGui() {
		frame.removeAll();
		frame.setVisible(false);
		JFrame frameNova = new JFrame("Code Smells Detector");
		setFrame(frameNova);
		buildGUI();	
	}

	private JPanel criarCentro() {
		int i = 1;
		JPanel mid = new JPanel();
		mid.setLayout(new GridLayout(4,1));
		
		JPanel midMid = new JPanel();
		midMid.setLayout(new FlowLayout());
		for (Component o : getLista()) {
			midMid.add(o);
		}
		mid.add(midMid);
		while(i!= nAt && nAt != 5) {
			JPanel jp = criarLayout();
			mid.add(jp);
			i++;
		}
		return mid;

	}
	private static ArrayList<Component> novaLista(){
		ArrayList<Component> listaAtualizada = new ArrayList<Component>();
		listaAtualizada.add(new JComboBox(and_or));
		listaAtualizada.add(new JComboBox(rules));
		listaAtualizada.add(new JComboBox(signals));
		listaAtualizada.add(new JTextField(5));
		return listaAtualizada;
	}
	private JPanel criarLayout() {

		JPanel novoPainel = new JPanel();
		novoPainel.setLayout(new FlowLayout());
		for (Component o : novaLista()) 
			novoPainel.add(o);
		return novoPainel;
	}

	private void atualizar() {
		nAt++;
		refazGui();
	}

	public ArrayList<Component> getLista() {
		return lista;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
