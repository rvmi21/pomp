package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class InfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fname;
	private JTextField Lname;
	private JTextField email;
	private JPasswordField pw;
	private JPasswordField pwconfirm;
	private JLabel Indicator ;

	/**
	 * Launch the application.
	 */
	public static void foo(String[] args, boolean edit) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoFrame frame = new InfoFrame(args, edit);
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
	public InfoFrame(String[] args, boolean edit) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fname = new JTextField();
		fname.setBounds(45, 55, 180, 20);
		contentPane.add(fname);
		fname.setColumns(10);
		
		Lname = new JTextField();
		Lname.setColumns(10);
		Lname.setBounds(255, 55, 180, 20);
		contentPane.add(Lname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(45, 117, 390, 20);
		contentPane.add(email);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(45, 30, 124, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(255, 30, 124, 14);
		contentPane.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(45, 92, 124, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 155, 124, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password");
		lblRepeatPassword.setBounds(255, 155, 124, 14);
		contentPane.add(lblRepeatPassword);
		
		JButton btnNewButton = new JButton("validate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (email.getText().equals("") || fname.getText().equals("") || Lname.getText().equals("") || pw.getText().equals("") || pwconfirm.getText().equals("")) {
					Indicator.setText("please fill at least 1 of these fields.");
				} else {
					
					String Emailo = email.getText();
					String fnm = fname.getText();
					String lnm = Lname.getText();
					String poi = pw.getText();
					

					if (edit) {
						// to be filled 
					} else {
						AttributFrame att = new AttributFrame(args, Emailo, fnm, lnm, poi);
						att.ATTO(args, Emailo, fnm, lnm, poi);
						dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(146, 254, 180, 23);
		contentPane.add(btnNewButton);
		
		Indicator = new JLabel("");
		Indicator.setBounds(135, 233, 218, 14);
		contentPane.add(Indicator);
		
		pw = new JPasswordField();
		pw.setBounds(45, 180, 180, 20);
		contentPane.add(pw);
		
		pwconfirm = new JPasswordField();
		pwconfirm.setBounds(255, 180, 180, 20);
		contentPane.add(pwconfirm);
	}
}
