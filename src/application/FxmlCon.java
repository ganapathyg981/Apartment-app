package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FxmlCon implements Initializable {

	@FXML
	private Button button;
	@FXML
	private TextField userblock;
	@FXML
	private PasswordField passblock;
	@FXML
	private Label errorcode;
	@FXML
	public void Handleclick(ActionEvent e) throws IOException
	{
		Parent homePage = FXMLLoader.load(getClass().getResource("homePage.fxml"));
		Scene scene = new Scene(homePage);
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		if(isValid())
		{
			stage.setResizable(false);	
		stage.setScene(scene);
		stage.show();
	}
		else {
			userblock.clear();
			passblock.clear();
			errorcode.setText("thappu da");
			
			
		}
	}
	public boolean isValid()
	{
		boolean letIn=false;
		System.out.println("SELECT * FROM Users WHERE USERNAME= "+ "'" +userblock.getText()+"'" +" AND PASSWORD ="+"'"+passblock.getText()+"'");
		Connection c =null;
		Statement stmnt =null;
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:C:\\\\first.db");
			c.setAutoCommit(false);
			System.out.println("Opened database Sucessfully");
			stmnt =c.createStatement();
			
			ResultSet rs = stmnt.executeQuery("SELECT * FROM Users WHERE USERNAME= "+ "'" +userblock.getText()+"'" +" AND PASSWORD ="+"'"+passblock.getText()+"'");
			while(rs.next())
			{
				if(rs.getString("USERNAME")!=null&&rs.getString("PASSWORD")!=null)
{
	String username =rs.getString("USERNAME");
	String password =rs.getString("PASSWORD");
	System.out.println("USERNAME = "+username);
	System.out.println("PASSWORD = "+password);
	letIn=true;
}

			}
//			rs.close();
//			c.close();
//			stmnt.close();
			
		}
		catch(Exception e)
		{
			System.err.println(e.getClass().getName()+" : "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Success");
		return letIn;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
}
