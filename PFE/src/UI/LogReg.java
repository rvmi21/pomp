package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import QRPack.QRManager;
import SQL.SQLManager;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class LogReg extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void poompe(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogReg frame = new LogReg(args);
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
	public LogReg(String[] args) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Login = new JButton("Login (Import QR code)");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("D:\\peepeepoopoo\\PFE");
				int result = fileChooser.showOpenDialog(contentPane);
				
				//may need later
				
				//FileFilter docFilter = new FileTypeFilter(".docx", "Microsoft Word Documents");
				//FileFilter pdfFilter = new FileTypeFilter(".pdf", "PDF Documents");
				//FileFilter xlsFilter = new FileTypeFilter(".xlsx", "Microsoft Excel Documents");
				//fileChooser.addChoosableFileFilter(docFilter);
				//fileChooser.addChoosableFileFilter(pdfFilter);
				//fileChooser.addChoosableFileFilter(xlsFilter);
				
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    String QRDescription = QRManager.readQRCode(selectedFile.getAbsolutePath());
				    String info[] = SQLManager.InfoUser(QRDescription);
				    if (info[0]==null) {
				    	lblNewLabel_3.setVisible(true);
					} else {
						System.out.println(info[0]);
						System.out.println(info[1]);
						System.out.println(info[2]);
						System.out.println(info[3]);
						Home HomeFrame = new Home(selectedFile.getAbsolutePath(), info[1]+info[2]);
						HomeFrame.sain(args, selectedFile.getAbsolutePath(), info[1]+info[2]);
						dispose();
						
					}
				}
			}
		});
		Login.setBounds(10, 177, 152, 39);
		contentPane.add(Login);
		
		Line2D lin = new Line2D.Float(0, 50, 100, 50);
		
		JButton Register = new JButton("Register");
		Register.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				InfoFrame f = new InfoFrame(args, false);
				f.foo(args, false);
				dispose();
			}
		});
		Register.setBounds(193, 177, 152, 39);
		contentPane.add(Register);
		
		JLabel lblNewLabel = new JLabel("or");
		lblNewLabel.setBounds(172, 189, 20, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lost your QR, click  ");
		lblNewLabel_1.setBounds(99, 230, 152, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = 	new JLabel("here");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(210, 230, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("QR not recognized");
		lblNewLabel_3.setBounds(126, 156, 104, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\peepeepoopoo\\PFE\\lock (4).png"));
		lblNewLabel_4.setBounds(110, 11, 128, 134);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_3.setVisible(false);
	}
}
