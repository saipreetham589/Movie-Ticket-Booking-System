package ers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class bookTicket extends JFrame {
	private JPanel contentPane;
	private static bookTicket obj;

	String username;
	private JTextField theatreNameTextField;
	private JTextField movieNameTextField;
	private JTextField dateTextField;
	private JTextField showTimingsTextField;
	private JTextField numerOfTicketsTextField;
//	private JTextArea dateTextArea;
	private bookTicket(final String username) {
		this.username=username;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 835, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel EntertheatrenameLabel = new JLabel("Theatre name");
		EntertheatrenameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		EntertheatrenameLabel.setBounds(83, 137, 136, 13);
		panel.add(EntertheatrenameLabel);
		
		theatreNameTextField = new JTextField();
		theatreNameTextField.setBounds(198, 133, 261, 25);
		panel.add(theatreNameTextField);
		theatreNameTextField.setColumns(10);
		
		JLabel movieNameLabel = new JLabel("Movie name");
		movieNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		movieNameLabel.setBounds(83, 204, 136, 13);
		panel.add(movieNameLabel);
		
		movieNameTextField = new JTextField();
		movieNameTextField.setColumns(10);
		movieNameTextField.setBounds(198, 200, 261, 25);
		panel.add(movieNameTextField);
		//movieNameTextField.setEditable(false);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateLabel.setBounds(83, 263, 136, 13);
		panel.add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setColumns(10);
		dateTextField.setBounds(198, 262, 261, 25);
		panel.add(dateTextField);
		
		JButton checkBtn = new JButton("Check");
		checkBtn.setForeground(Color.WHITE);
		checkBtn.setBackground(Color.BLUE);
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String theatre_Name=theatreNameTextField.getText();
				String movieName=movieNameTextField.getText();
				
				@SuppressWarnings("resource")
				MongoClient mongoClient2 = new MongoClient("localhost",27017);
				MongoDatabase db2 = mongoClient2.getDatabase("theatreDB");
				MongoCollection<Document> movieCollection=db2.getCollection("movieDetails");
				BasicDBObject criteria = new BasicDBObject();
				criteria.append("movieName",movieName );
				criteria.append("theatreName",theatre_Name);
				Document d =movieCollection.find(criteria).first();
				if(d==null) {
					JOptionPane.showMessageDialog(new JFrame(), "Enter valid theatre name and movie name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else{
					showTimingsTextField.setText(d.get("showTime").toString());	
				}
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBtn.setBounds(514, 135, 85, 21);
		panel.add(checkBtn);
		
		
		JButton bookBtn = new JButton("BOOK");
		bookBtn.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		bookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String theatre_Name=theatreNameTextField.getText();
				String movieName=movieNameTextField.getText();
				String showTimings=showTimingsTextField.getText();
				String numerOfTickets=numerOfTicketsTextField.getText();
				String date=dateTextField.getText();
				
				
				@SuppressWarnings("resource")
				MongoClient mongoClient2 = new MongoClient("localhost",27017);
				MongoDatabase db2 = mongoClient2.getDatabase("theatreDB");
				MongoCollection<Document> movieCollection=db2.getCollection("movieDetails");
				BasicDBObject criteria = new BasicDBObject();
				criteria.append("movieName",movieName );
				criteria.append("theatreName",theatre_Name);
				Document d =movieCollection.find(criteria).first();
				if(d==null) {
					JOptionPane.showMessageDialog(new JFrame(), "Enter valid theatre name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else{
					@SuppressWarnings("resource")
					MongoClient mongoClient = new MongoClient("localhost",27017);
					MongoDatabase db = mongoClient.getDatabase("theatreDB");
					MongoCollection<Document> bookedTicketCollection=db.getCollection("bookedTickets");
					
					@SuppressWarnings("resource")
					MongoClient mongoClient3 = new MongoClient("localhost",27017);
					MongoDatabase db3 = mongoClient3.getDatabase("theatreDB");
					MongoCollection<Document> userCollection=db3.getCollection("bookedTickets");
					BasicDBObject criteria2 = new BasicDBObject();
					criteria2.append("username",username);
					criteria2.append("theatreName",theatre_Name);
					Document d4 = userCollection.find(criteria2).first();
					int price = Integer.valueOf(d.get("price").toString());
					if(d4!=null) {
						price=(int)(price*0.8);
					}
					
					
//					int price = Integer.valueOf(d.get("price").toString());
					String sprice = String.valueOf(price);
					int tprice = price*Integer.valueOf(numerOfTicketsTextField.getText());
					String s = String.valueOf(tprice);
					
					
					
					
					Document doc =new Document("username",username);
					doc.append("theatreName", theatre_Name);
					doc.append("movieName",movieName);
					doc.append("showTimings",showTimings);
					doc.append("numerOfTickets",numerOfTickets);
					doc.append("date",date);
					doc.append("price", sprice);
					doc.append("final price", s);
					
					bookedTicketCollection.insertOne(doc);
					JOptionPane.showMessageDialog(new JFrame(), "Registartion successful","Movie Ticket Booked Successfully",JOptionPane.INFORMATION_MESSAGE);
					
				}	
			    
			}

		});
		bookBtn.setBounds(83, 459, 85, 21);
		panel.add(bookBtn);
		
		JLabel showTimingsLabel = new JLabel("Show Timings");
		showTimingsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		showTimingsLabel.setBounds(86, 316, 106, 13);
		panel.add(showTimingsLabel);
		
		showTimingsTextField = new JTextField();
		showTimingsTextField.setBounds(202, 315, 257, 19);
		panel.add(showTimingsTextField);
		showTimingsTextField.setColumns(10);
		showTimingsTextField.setEditable(false);
		
		JLabel numberOfTicketsLabel = new JLabel("Number of Tickets");
		numberOfTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numberOfTicketsLabel.setBounds(83, 373, 115, 13);
		panel.add(numberOfTicketsLabel);
		
		numerOfTicketsTextField = new JTextField();
		numerOfTicketsTextField.setBounds(205, 371, 254, 19);
		panel.add(numerOfTicketsTextField);
		numerOfTicketsTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Payment Type");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(83, 409, 102, 13);
		panel.add(lblNewLabel);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("UPI");
		tglbtnNewToggleButton.setBounds(204, 407, 255, 21);
		panel.add(tglbtnNewToggleButton);
		
	}
	public static bookTicket getInstance(final String username) {
		if(obj!=null) {
			return obj;
		}
		obj = new bookTicket(username);
		return obj;
	}
}
