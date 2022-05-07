package ers;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class showMovies extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public showMovies() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1034, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase db = mongoClient.getDatabase("theatreDB");
		MongoCollection<Document> collection=db.getCollection("movieDetails");
		
		
		String[][] trans= new String[11][5];
		int i=1;
		trans[0][0]="movie Name";
		trans[0][1]="theatre Name";
		trans[0][2]="show Time";
		trans[0][3]="capacity";
		trans[0][4]="ticket price";
		
		MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext() && i<10) {
        	Document f=cursor.next();
            trans[i][0]=f.get("movieName").toString();
            trans[i][1]=f.get("theatreName").toString();
            trans[i][2]=f.get("showTime").toString();
            trans[i][3]=f.get("capacity").toString();
            trans[i][4]=f.get("price").toString();
            i+=1;
            
        }
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
				trans,
				new String[] {
				"New column", "New column", "New column", "New column","New column"
			}
		));
		table_2.setBounds(44, 79, 456, 282);
		contentPane.add(table_2);
		
		JLabel lblNewLabel = new JLabel("Available movies");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		lblNewLabel.setBounds(216, 10, 138, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Theatre name");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(57, 50, 105, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Movie");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(172, 50, 110, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Show timings");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(278, 53, 95, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(396, 56, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Vasavi\\Desktop\\cs320\\KGF-2-Box-Office-Will-Easily-Beat-RRR.jpg"));
		lblNewLabel_5.setBounds(554, 10, 456, 351);
		contentPane.add(lblNewLabel_5);
		
		
		
		
		
		
    	
		
	}
}
