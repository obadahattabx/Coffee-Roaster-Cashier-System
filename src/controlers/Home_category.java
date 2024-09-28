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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Category;

public class Home_category implements Initializable {
	  private  int idofselected;
	  private  String getofselected;
	  private Connection con;
	  private ArrayList<Category> data_cat;
	  private ObservableList<Category> data_cat_b;
	  private Stage stage_category;
public void setStage_category(Stage stage_category) {
		this.stage_category = stage_category;
	}


@FXML private TableView<Category> tablecat;
@FXML private TableColumn<Category, String> culmname;
@FXML private TableColumn<Category, Integer> culmnum ; 
@FXML private TextField text_category;
@FXML private Button baddcategory;
@FXML private Button bonSaveCategory;
@FXML private Button onEditCategory;

	


@FXML private void onNewCategory() {
	baddcategory.setDisable(false);
	text_category.setDisable(false);
}
@FXML private void addcategory() throws ClassNotFoundException, SQLException {
	data_cat=new ArrayList<Category>();
	con=ConnectionDB.connectDB();
	PreparedStatement stmt =con.prepareStatement("Insert into category (cname) values ('"+text_category.getText()+"');");
	stmt.executeUpdate();
	stmt.close();
	con.close();
	getdata_category() ;
	this.tablecat.setItems(data_cat_b);
	text_category.setDisable(true);
	baddcategory.setDisable(true);
}
	
	private void  getdata_category() throws ClassNotFoundException, SQLException {
		data_cat=new ArrayList<Category>();
		int count=1;
		 con = ConnectionDB.connectDB();
		Statement stmt= con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from category");
		while(rs.next()) {
			data_cat.add(new Category(Integer.parseInt(rs.getString(1)),count,rs.getString(2)));
			count++;
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		data_cat_b=FXCollections.observableArrayList(data_cat);
	}
	private void tableview_action() {
		tablecat.setOnMouseClicked(e->{
			Category c=tablecat.getItems().get(tablecat.getSelectionModel().getSelectedIndex());
			text_category.setText(c.getCname());
			getofselected=c.getCname();
			idofselected=c.getCid();
			text_category.setText(getofselected);
			onEditCategory.setOnAction(e1->{
				bonSaveCategory.setDisable(false);
				text_category.setDisable(false);
			});
		});
		
	}

@FXML private void onSaveCategory() throws ClassNotFoundException, SQLException {
	 con = ConnectionDB.connectDB();
	 PreparedStatement pstmt=con.prepareStatement("update  category set cname='"+text_category.getText()+"' where cid="+idofselected+"   ");
	 pstmt.executeUpdate();
	 pstmt.close();
	 con.close();
	 bonSaveCategory.setDisable(true);
	 text_category.setDisable(true);
	 getdata_category();
	 tablecat.setItems(data_cat_b);

}

@FXML private  void onselect() {
	stage_category.close();
	
}
public  int  getcatofid() {
	return idofselected;
}
public  String  getofname() {
	return text_category.getText();
}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			getdata_category();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		culmname.setCellValueFactory(new PropertyValueFactory<>("cname") );
		culmnum.setCellValueFactory(new PropertyValueFactory<>("count") );
		this.tablecat.setItems(data_cat_b);
		tableview_action();
		
		
	}
}
