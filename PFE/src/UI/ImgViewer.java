package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImgViewer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void Blyat(String path) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImgViewer frame = new ImgViewer(path);
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
	public ImgViewer(String path) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
	    BufferedImage img;
		try {
			img = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(img);
			lblNewLabel.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(10, 11, 628, 628);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(278, 650, 89, 23);
		contentPane.add(btnNewButton);
	}
}
