package ers;

import static com.mongodb.client.model.Filters.eq;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.awt.Font;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTextField theatreNameTextField;
	private JTextField movieNameTextField;
	private JTextField showTimeTextField;
	private JButton logoutButton;
	private JTextField capacitytextField;
	private JLabel PriceLabel;
	private JTextField pricetextField;
	
	
	public Admin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel theatreNameLabel = new JLabel("Theatre name");
		theatreNameLabel.setBounds(50, 57, 89, 25);
		contentPane.add(theatreNameLabel);
		
		JLabel movieNameLabel = new JLabel("movie name");
		movieNameLabel.setBounds(50, 105, 89, 25);
		contentPane.add(movieNameLabel);
		
		JLabel showTimeLabel = new JLabel("show time");
		showTimeLabel.setBounds(50, 151, 89, 25);
		contentPane.add(showTimeLabel);
		
		theatreNameTextField = new JTextField();
		theatreNameTextField.setBounds(207, 60, 96, 19);
		contentPane.add(theatreNameTextField);
		theatreNameTextField.setColumns(10);
		
		movieNameTextField = new JTextField();
		movieNameTextField.setBounds(207, 108, 96, 19);
		contentPane.add(movieNameTextField);
		movieNameTextField.setColumns(10);
		
		showTimeTextField = new JTextField();
		showTimeTextField.setBounds(207, 154, 96, 19);
		contentPane.add(showTimeTextField);
		showTimeTextField.setColumns(10);
		
		JButton addButton = new JButton("add");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String theatreName = theatreNameTextField.getText();
				String movieName = movieNameTextField.getText();
				String showTime = showTimeTextField.getText();
				String capacity = capacitytextField.getText();
				String price = pricetextField.getText();
				MongoClient mongoClient = new MongoClient("localhost",27017);
				MongoDatabase db = mongoClient.getDatabase("theatreDB");
				MongoCollection<Document> collection=db.getCollection("movieDetails");
				Document doc =new Document("movieName",movieName);
				doc.append("theatreName",theatreName);
				doc.append("showTime",showTime);
				doc.append("capacity",capacity);
				doc.append("price",price);
				collection.insertOne(doc);
				JOptionPane.showMessageDialog(new JFrame(), "Success","movie details added successfully",JOptionPane.OK_OPTION);
				mongoClient.close();
				
			}
		});
		addButton.setBounds(50, 303, 85, 21);
		contentPane.add(addButton);
		
		logoutButton = new JButton("logout");
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		logoutButton.setBounds(207, 303, 85, 21);
		contentPane.add(logoutButton);
		
		JLabel capacityLabel = new JLabel("capacity");
		capacityLabel.setBounds(50, 209, 45, 13);
		contentPane.add(capacityLabel);
		
		capacitytextField = new JTextField();
		capacitytextField.setBounds(207, 206, 96, 19);
		contentPane.add(capacitytextField);
		capacitytextField.setColumns(10);
		
		PriceLabel = new JLabel("Price");
		PriceLabel.setBounds(50, 256, 45, 13);
		contentPane.add(PriceLabel);
		
		pricetextField = new JTextField();
		pricetextField.setBounds(207, 253, 96, 19);
		contentPane.add(pricetextField);
		pricetextField.setColumns(10);
		
	
	}
}
