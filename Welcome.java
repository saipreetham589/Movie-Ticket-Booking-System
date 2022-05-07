package ers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	

	/**
	 * Create the frame.
	 */
	String username;
	public Welcome(final String username) {
		this.username=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1127, 668);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(0, 10, 212, 551);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnShowMovies = new JButton("Movies");
		btnShowMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMovies s= new showMovies();
				s.setVisible(true);
			}
		});
		btnShowMovies.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnShowMovies.setForeground(new Color(255, 255, 255));
		btnShowMovies.setBackground(Color.BLACK);
		btnShowMovies.setBounds(10, 246, 192, 21);
		panel_1.add(btnShowMovies);
		
		JButton btnTicketBooking = new JButton("Ticket Booking");
		btnTicketBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTicket eR= bookTicket.getInstance(username);
				eR.setVisible(true);
				
			}
		});
		btnTicketBooking.setForeground(Color.WHITE);
		btnTicketBooking.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTicketBooking.setBackground(Color.BLACK);
		btnTicketBooking.setBounds(10, 292, 192, 21);
		panel_1.add(btnTicketBooking);
		
		JButton btnBookings = new JButton("Bookings");
		btnBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookedMovies r=new bookedMovies(username);
				r.setVisible(true);

			}
		});
		btnBookings.setForeground(Color.WHITE);
		btnBookings.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBookings.setBackground(Color.BLACK);
		btnBookings.setBounds(10, 342, 192, 21);
		panel_1.add(btnBookings);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(10, 530, 192, 21);
		panel_1.add(btnLogout);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Vasavi\\Desktop\\cs320\\0-2.jpg"));
		lblNewLabel_2.setBounds(222, 10, 881, 471);
		contentPane.add(lblNewLabel_2);
	}
}
