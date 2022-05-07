package ers;

import static com.mongodb.client.model.Filters.eq;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class bookedMovies extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	
	String username;
	private JTable table_2;
	public bookedMovies(String username) {
		this.username=username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 977, 584);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase db = mongoClient.getDatabase("theatreDB");
		MongoCollection<Document> collection=db.getCollection("bookedTickets");
		
		
		String[][] trans= new String[11][7];
		int i=1;
		trans[0][0]="customer name";
		trans[0][1]="theatre name";
		trans[0][2]="show timings";
		trans[0][3]="movie name";
		trans[0][4]="num of tickets";
		trans[0][5]="price";
		trans[0][6]="total price";
		
		
		MongoCursor<Document> cursor = collection.find(eq("username",username)).iterator();
        while (cursor.hasNext() && i<10) {

        	Document f=cursor.next();
            trans[i][0]=f.get("username").toString();
            trans[i][1]=f.get("theatreName").toString();
            trans[i][2]=f.get("showTimings").toString();
            trans[i][3]=f.getString("movieName");
            trans[i][4]=f.getString("numerOfTickets");
            trans[i][5]=f.getString("price");
            trans[i][6]=f.getString("final price");
            i+=1;
            
        }
        table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			trans,
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_2.setBounds(49, 139, 796, 348);
		contentPane.add(table_2);		
	}
}
