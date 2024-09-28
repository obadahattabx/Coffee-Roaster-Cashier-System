package controlers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;
import model.ProductWithQuntitiy;
import model.Users;

public class Home_addUsers implements Initializable {
	
	private int uid;
	  private ArrayList<Users> data_user;
	  private ObservableList<Users> data_user_b;
@FXML private TextField text_username;
@FXML private TextField text_password;
@FXML private RadioButton rwork;
@FXML private RadioButton rmanag;
@FXML private Button Deleteusers;
@FXML private TableView<Users> table_user;
@FXML private TableColumn<Users, String>Tusername;
@FXML private TableColumn<Users, String> Tpassword;
@FXML private TableColumn<Users, String> Tsate;
@FXML private TableColumn<Users, String> Tname;

@FXML private TextField text_name;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			getdate_user();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		Tname.setCellValueFactory(new PropertyValueFactory<>("namefull"));
		Tusername.setCellValueFactory(new PropertyValueFactory<>("username"));
		Tpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		Tsate.setCellValueFactory(new PropertyValueFactory<>("state"));
		table_user.setItems(data_user_b);
		
	}
	@FXML private void onAddUser() throws ClassNotFoundException, SQLException {
		if(text_name.getText().isEmpty()||text_password.getText().isEmpty()||text_password.getText().isEmpty()){
			Alert(".... √ﬂœ „‰  ⁄»∆… «·Ã„Ì⁄ ");
			text_name.setText("");
			text_username.setText("");
			text_password.setText("");
			return;
		}
		InsertUser();
		getdate_user();
		table_user.setItems(data_user_b);
		text_name.setDisable(true);
		text_username.setDisable(true);
		text_password.setDisable(true);
		rwork.setDisable(true);
		rmanag.setDisable(true);
	}
	@FXML private void onNewUsers() {
		text_name.setDisable(false);
		text_username.setDisable(false);
		text_password.setDisable(false);
		rwork.setDisable(false);
		rmanag.setDisable(false);
		text_name.setText("");
		text_username.setText("");
		text_password.setText("");
		
	}
	@FXML private void onEditUsers() {
		table_action();
		text_name.setDisable(true);
		text_username.setDisable(true);
		text_password.setDisable(true);
		rwork.setDisable(true);
		rmanag.setDisable(true);
		Deleteusers.setDisable(false);
	}
	@FXML private void onDeleteUsers() throws ClassNotFoundException, SQLException {
		Connection con = ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("delete from users where uid="+uid+"; ");
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		Deleteusers.setDisable(true);
		getdate_user();
		table_user.setItems(data_user_b);
		
		
	}
	private void Alert(String s) {
	 	Alert alert =new Alert(AlertType.WARNING);
	  alert.setTitle("Â‰«ﬂ Œÿ√");
	  alert.setHeaderText(null);
	  alert.setContentText(s);
	  alert.showAndWait();
	  
}
	
	private void getdate_user() throws ClassNotFoundException, SQLException {
		data_user=new ArrayList<Users>();
		
		Connection con = ConnectionDB.connectDB();
		Statement stmt= con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from users");
		while(rs.next()) {
			data_user.add(new Users(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		data_user_b=FXCollections.observableArrayList(data_user);
	}
	private void InsertUser() throws ClassNotFoundException, SQLException {
		String S;
		if(rmanag.isSelected()) {
			S="m";
		}
		else {
			S="w";
		}
		Connection con = ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("insert into users (namefull,username,upassword,ustate) values "
				+ "('"+text_name.getText()+"','"+text_username.getText()+"','"+text_password.getText()+"','"+S+"'); ");
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}
	private void table_action() {
		table_user.setOnMouseClicked(e->{
			Users PQ=table_user.getItems().get
					(table_user.getSelectionModel().getSelectedIndex());
			text_name.setText(PQ.getNamefull());
			text_username.setText(PQ.getUsername());
			text_password.setText(PQ.getPassword());
			uid=PQ.getId();
			if(PQ.getState().equals("m")) {
				rmanag.setSelected(true);
			}
			else {
				rwork.setSelected(true);
			}

			
			
			
		});
	}

}
