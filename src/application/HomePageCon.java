package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageCon implements Initializable {
     @FXML
     private Button addb,viewb,exitb;
     @FXML
     public void Exit()
     {
    	 System.exit(0);
     }
     @FXML
     public void View(ActionEvent e) throws IOException
     {
    	 Parent homePage = FXMLLoader.load(getClass().getResource("viewfx.fxml"));
 		Scene scene = new Scene(homePage);
 		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
 		stage.setResizable(false);
 		stage.setScene(scene);
		stage.show();
     }
     @FXML
     public void Add(ActionEvent e) throws IOException
     {
    	 Parent homePage = FXMLLoader.load(getClass().getResource("addfx.fxml"));
 		Scene scene = new Scene(homePage);
 		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
 		stage.setResizable(false);
 		stage.setScene(scene);
		stage.show();
     }
	@FXML
     public void Print(ActionEvent e) throws IOException
     {
    	 Parent homePage = FXMLLoader.load(getClass().getResource("Print.fxml"));
 		Scene scene = new Scene(homePage);
 		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
 		stage.setResizable(false);
 		stage.setScene(scene);
		stage.show();
     }
     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
