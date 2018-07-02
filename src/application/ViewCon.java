package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
public class ViewCon implements Initializable {

	private ObservableList<Bill> l1 = FXCollections.observableArrayList();
    LocalDate from,to;
     @FXML   
	private Button viewb,backb;
     @FXML
     private DatePicker fromp,top;
     @FXML
     private TableView<Bill> bill;
     @FXML
     private TableColumn<Bill, String> name;
     @FXML
     private TableColumn<Bill, String> rent;
     @FXML
     private TableColumn<Bill, String> date;
     @FXML
     private TableColumn<Bill, String> total;
     @FXML
     private TableColumn<Bill, String> maintanance;
     @FXML
     private TableColumn<Bill, String> eb;
     @FXML
     private TableColumn<Bill, String> sewage;
     @FXML
     private TableColumn<Bill, String> room;
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
     @FXML
     public void view(ActionEvent e1) throws IOException
     {
    	 from=fromp.getValue();
 		to=top.getValue();
 		Connection c=null;
		Statement stmnt =null;
		ResultSet rs=null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:C:\\\\first.db");
			c.setAutoCommit(false);
			System.out.println("Opened db Sucessfully");
			System.out.println(from+"  "+to);
			stmnt=c.createStatement();
			rs = stmnt.executeQuery("select * from Payments where timing between '"+from+"'"+" and '"+to+"';");
			while(rs.next())
			{
				String a=rs.getString("RoomNo");
				String b=rs.getString("Name");
				String u=rs.getString("Rent");
				String d=rs.getString("Maintanance");
				String e=rs.getString("Eb");
				String f=rs.getString("Sewage");
				String g=rs.getString("total");
				String day =rs.getObject("Timing").toString();
				
				l1.add(new Bill(a,b,u,d,e,f,g,day));
			}
			bill.setItems(l1);
			name.setCellValueFactory(cellData -> cellData.getValue().getName());
			date.setCellValueFactory(cellData -> cellData.getValue().getTiming());
			maintanance.setCellValueFactory(cellData -> cellData.getValue().getMaintanance());
			eb.setCellValueFactory(cellData-> cellData.getValue().getEb());
            rent.setCellValueFactory(cellData-> cellData.getValue().getRent());
            sewage.setCellValueFactory(cellData-> cellData.getValue().getSewage());
            total.setCellValueFactory(cellData-> cellData.getValue().getTotal());
            room.setCellValueFactory(cellData-> cellData.getValue().getRoomNo());
            
			
			
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		
		
    	 
     }
     
     
     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	    
	
	
}
