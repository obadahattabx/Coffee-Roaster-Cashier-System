package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import controlers.Home;
import controlers.controller_login;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/interfaces/login.fxml"));
			Pane P =(Pane) loader.load();
			
			controller_login cl = loader.getController();
			cl.setStage_login(primaryStage);
			Scene scene = new Scene(P );
			Home.setStagelogin(primaryStage,cl.getUsername(),cl.getPassword());
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setOpacity(0.95);
			
	
			
			
			primaryStage.setTitle("ALMKHTAR");
			primaryStage.setScene(scene);
			Image ime= new Image("/icon/almkhtar.png");	
			primaryStage.getIcons().add(ime);
			primaryStage.show();
			
		
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
