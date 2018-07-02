package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCon implements Initializable {
    int x=0;
    Connection cnc =null;
	Statement stmnt =null;
    int prev;
	String a,b,c,d,n,f,g,o;
	LocalDate day;
	@FXML
	private Button addb,backb,clearb;
	@FXML
	private TextField name,rent,maintanance,sewage,total,eb,unit;
	@FXML
	private MenuButton room_no;
	@FXML
	private DatePicker date;
	@FXML
	public void clear(ActionEvent o)
	{
		name.clear();
		rent.clear();
		maintanance.clear();
		sewage.clear();
		total.clear();
		eb.clear();
		room_no.setText("Room");
		date.getEditor().clear();
		unit.clear();
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
	@FXML
	public void select(ActionEvent e1)
	{
	MenuItem menu = (MenuItem) e1.getSource();
	room_no.setText(menu.getText());
	}
	@FXML 
	public void Addbutton(ActionEvent e1) throws SQLException
	{
		if(allset())
		{
		setEverything();
		String query ="insert into Payments (RoomNo,Name,Rent,Maintanance,Eb,Sewage,total,Timing,unit) Values('"
				+a+"','"+b+"','"+c+"','"+d+"','"+n+"','"+f+"','"+g+"','"+day+"','"+o+"');";
		insertPayment(query);
		
		
	}
		else {
			System.out.println("works");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
		
			alert.setHeaderText(null);
			alert.setContentText("Check the input");

			alert.showAndWait();
		}
	}
	@FXML
	public void setEverything()
	{   a=room_no.getText();
    	prev=lastunit(a);
//	prev = x;
	
		
		o=unit.getText();
		b=name.getText();
		c=rent.getText();
		d=maintanance.getText();
		int b =(Integer.parseInt(o)-prev)*7;
		String def =String.valueOf(b);
		eb.setText(def);
		n=unit.getText();
		
		f=sewage.getText();
		int a=Integer.parseInt(c)+Integer.parseInt(d)+Integer.parseInt(n)+Integer.parseInt(f);
		String abc=String.valueOf(a);
		total.setText(abc);
		g=total.getText();
		day=date.getValue();
		
	}
	@FXML
	public void insertPayment(String query) throws SQLException
	{
		
		
			stmnt =cnc.createStatement();
			System.out.println(query);
			stmnt.executeUpdate(query);
			stmnt.close();
			cnc.commit();
			cnc.close();
			System.out.println("succesfully inserted");
			}
	@FXML
	public boolean allset()
	{
		boolean a=true;
		if(room_no.getText().equals("Room")||unit.getText().isEmpty()||name.getText().isEmpty()||maintanance.getText().isEmpty()||rent.getText().isEmpty()||sewage.getText().isEmpty()||date.equals(null))
			a=false;
		else if(isInt(room_no.getText())||isInt(unit.getText())||isInt(maintanance.getText())||isInt(rent.getText())||isInt(sewage.getText()))
				a=false;
		return a;
		
	}
	
	@FXML
	public boolean isInt(String test)
	{
		try {
			int a= Integer.parseInt(test);
			return false;
		}
		catch(NumberFormatException e) {
		return true;
		}
	}
	@FXML
	public void startCon()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			cnc=DriverManager.getConnection("jdbc:sqlite:C:\\first.db");
			cnc.setAutoCommit(false);
			System.out.println("Opened database Sucessfully");
		}
		catch(Exception e1)
		{
			System.err.println(e1.getClass().getName()+":"+e1.getMessage());
		}
	}
		
		@FXML
		public int lastunit(String b)
		{
			String query = "select unit from payments where unit = (select max(unit) from payments)and roomno ='"+b+"';";
			
			Statement stmnt =null;
			ResultSet rs;
			try {
				startCon();
				stmnt =cnc.createStatement();
				System.out.println(query);
				rs=stmnt.executeQuery(query);
				String unit = rs.getString("unit");
				int x = Integer.parseInt(unit);
				stmnt.close();
				cnc.commit();
				
				System.out.println("succesfully updated");
				return x;
				
				
				
			}
			catch(Exception e1)
			{
				return 0;
			}
			
		}
		
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
