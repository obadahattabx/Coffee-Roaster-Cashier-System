package controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;



import application.ConnectionDB;
import application.MakDragable;
import application.UserAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Users;

public class controller_login implements Initializable{
@FXML private TextField username;
@FXML private PasswordField password;
@FXML private HBox Husername; 
@FXML private AnchorPane parent;
	  private MakDragable md =new MakDragable();

private ArrayList<Users> data;
 private int i;
 private Stage stage_login;
 
 
 // stage alert because there miskate
 private void Alert(String s) {
	 	Alert alert =new Alert(AlertType.WARNING);
	  alert.setTitle("Â‰«ﬂ Œÿ√");
	  alert.setHeaderText(null);
	  alert.setContentText(s);
	  alert.showAndWait();
	  
}
 // open interface controlpanel by button login
	public void onlogin() throws ClassNotFoundException, SQLException, IOException {
		data=new ArrayList<Users>();

		if(getdata()) {
			if(data.get(i).getState().equals("m")) {
				BorderPane homepage=FXMLLoader.load(getClass().getResource("/interfaces/controlpanel.fxml"));
				UserAdd.setNameadduser(data.get(i).getNamefull());
				Scene se=new Scene(homepage);
				Stage app=new Stage();
				app.setScene(se);
				app.setMaximized(true);
				Image ime= new Image("/icon/almkhtar.png");	
				app.getIcons().add(ime);
				Home.setStagehome(app);		
				
				
				app.show();
				stage_login.close();
			
			}
			else {
				System.out.println("connected worker");
			}
		}
		else {
			Alert("Â‰«ﬂ Œÿ√ ›Ì «”„ «·„” Œœ„ «Ê ﬂ·„… «·„—Ê—");
			System.out.println("wrong!");
		}
		
		
		
	}
	
// getdata from mysql....
	
	public boolean getdata() throws ClassNotFoundException, SQLException {
		Connection con =ConnectionDB.connectDB();
		Statement stmt= con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from users");
		while(rs.next()) {
			Users us=new Users();
			us.setNamefull(rs.getString(2));
			us.setUsername(rs.getString(3));
			us.setPassword(rs.getString(4));
			us.setState(rs.getString(5));
			data.add(us);

		}
	
		rs.close();
		stmt.close();
		con.close();
		i=0;
		while(data.size()!=i) {
			if(data.get(i).getUsername().equals(username.getText())&&
					data.get(i).getPassword().equals(password.getText())) {
				System.out.println("succfull connected");
				return true;
			}
			i++;
		}
		return false;
	}
	
	public void setStage_login(Stage stage_login) {
		this.stage_login = stage_login;
	}
	
	@FXML private void onCloseStage() {
		stage_login.close();
	}
	@FXML private void onMini(MouseEvent event) {
		Stage s=(Stage) ((Node)event.getSource()).getScene().getWindow();
		s.setIconified(true);
	}

	public TextField getUsername() {
		return username;
	}
	public PasswordField getPassword() {
		return password;
	}
	 public HBox getHusername() {
			return Husername;
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		md.makeDragable(parent);
		password.setOnAction(e->{
			try {
				onlogin() ;
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
}
