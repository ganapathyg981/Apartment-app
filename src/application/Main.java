package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root =FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Ette");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
