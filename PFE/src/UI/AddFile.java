package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SQL.SQLManager;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AddFile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void sain(String[] args, String Owner, Boolean edit) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFile frame = new AddFile(Owner, edit);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddFile(String Owner, Boolean edit) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\peepeepoopoo\\PFE\\lock (4).png"));
		lblNewLabel.setBounds(228, 29, 128, 128);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(111, 186, 459, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("File name :");
		lblNewLabel_1.setBounds(10, 189, 78, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("File path :");
		lblNewLabel_1_1.setBounds(10, 240, 78, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(111, 237, 353, 20);
		contentPane.add(textField_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(111, 283, 459, 207);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("browser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("D:\\peepeepoopoo\\PFE");
				int result = fileChooser.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    textField_1.setText(selectedFile.getAbsolutePath());
				    
				}
			}
		});
		btnNewButton.setBounds(481, 236, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("File Description :");
		lblNewLabel_1_1_1.setBounds(10, 283, 99, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Ow = Owner;
				String name = textField.getText();
				String path = textField_1.getText();
				String desc = textPane.getText();
				String ext = SQLManager.ext(path);
				String txt = "";
				try {
					txt = SQLManager.readFile(path);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					SQLManager.FileInsert(Ow, name, desc, path, ext, txt);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(481, 586, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Abort");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(375, 586, 89, 23);
		contentPane.add(btnNewButton_1_1);
		

	}
}
