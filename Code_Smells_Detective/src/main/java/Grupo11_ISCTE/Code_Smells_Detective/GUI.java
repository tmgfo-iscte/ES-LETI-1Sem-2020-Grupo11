package Grupo11_ISCTE.Code_Smells_Detective;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import javax.swing.filechooser.FileSystemView;


public class GUI {
	
	 public static void main(String args[]) {

		 
		 	String[] quality_evaluation= {"Textual","Tabular", "Gráfica"};
		 	String path = "C:/Users/Lenovo/Desktop/ES_Project/ES_Project/src/bad_code_simpsons.jpg";
		 	
	        //Creating the Frame
	        JFrame frame = new JFrame("Code Smells Detector");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 500);

	        //Creating the MenuBar and adding components
	        JMenuBar mb = new JMenuBar();
	        JMenu m1 = new JMenu("RULES");
	        mb.add(m1);
	        JMenuItem m11 = new JMenuItem("New");
	        JMenuItem m22 = new JMenuItem("Edit");
	        JMenuItem m33 = new JMenuItem("View..");
	        m1.add(m11);
	        m1.add(m22);
	        m1.add(m33);

	        //Creating the panel at bottom and adding components
	        JPanel panel = new JPanel(); // the panel is not visible in output
	        JButton excel = new JButton("Import Excel");
	        JComboBox quality = new JComboBox(quality_evaluation);
	        JButton send = new JButton("Check");
	        JButton reset = new JButton("Random");
	        quality.setSelectedIndex(2);
	        panel.add(excel); // Components Added using Flow Layout
	        panel.add(quality);
	        panel.add(send);
	        panel.add(reset);


	        // Icon at the Center
	        JLabel mid = new JLabel();
	        ImageIcon icon = new ImageIcon(path);
	        mid.setIcon(icon);

	        excel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnValue = jfc.showOpenDialog(null);
					if(returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						System.out.println(selectedFile.getAbsolutePath());
						String filePath = selectedFile.getAbsolutePath();
					}
				}
			});
	        
	        
	        //Adding Components to the frame.
	        frame.getContentPane().add(BorderLayout.SOUTH, panel);
	        frame.getContentPane().add(BorderLayout.NORTH, mb);
	        frame.getContentPane().add(BorderLayout.CENTER, mid);
	        frame.setVisible(true);
	        frame.pack();
	    }
	 
	 
	 
}

