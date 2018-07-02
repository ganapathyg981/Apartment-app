package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
public class PrintCon implements Initializable {
	private List<String> l;
	
	PrintWriter writer1 =null;
	 
	
	int i=0;
    private LocalDate from,to;
	@FXML
	private DatePicker fromp,top;
	@FXML
	public void Print(ActionEvent e) throws FileNotFoundException
	{
		from=fromp.getValue();
		to=top.getValue();
		Connection c=null;
		Statement stmnt =null;
		ResultSet rs=null;
		List <String> l =new ArrayList<String>();
		
		
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:C:\\\\first.db");
			c.setAutoCommit(false);
			System.out.println("Opened db Sucessfully");
			System.out.println(from+"  "+to);
			stmnt=c.createStatement();
			writer1 = new PrintWriter(new File("C:\\test.txt")); 
			rs = stmnt.executeQuery("select * from Payments where timing between '"+from+"'"+" and '"+to+"';");
			while(rs.next())
			{
				String a=rs.getString("RoomNo");
				String b=rs.getString("Name");
				String u=rs.getString("Rent");
				String d=rs.getString("Maintanance");
				String e1=rs.getString("Eb");
				String f=rs.getString("Sewage");
				String g=rs.getString("total");
				String day =rs.getObject("Timing").toString();
				
				l.add(String.format("%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n\n","Room:\t\t",a,
						"Name:\t\t",b,"Rent:\t\t",u,"Maintenance:",d,"EB:\t\t\t",e1,"Total:\t\t",g));
				
				
			}
		}
		catch(Exception e2)
		{
			System.err.println(e2);
		}
		String[] ot = new String[l.size()];
		l.toArray(ot);
		for(String x: ot)
		{
			
			System.out.println(x);
		
			
	        writer1.write(x);                                                   
	                        
	         
		}
		writer1.close(); 
	}
	@FXML
	public void back(ActionEvent e1) throws IOException
	{
		Parent homePage = FXMLLoader.load(getClass().getResource("homePage.fxml"));
		Scene scene = new Scene(homePage);
		Stage stage = (Stage) ((Node)e1.getSource()).getScene().getWindow();
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
