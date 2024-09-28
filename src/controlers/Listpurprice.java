package controlers;

import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bill;

public class Listpurprice implements Initializable {
	
	  private String name;
	  private String purprice;
	  private String typequnt;
	  private String amount;

	public String getName() {
		return name;
	}

	public String getPurprice() {
		return purprice;
	}

	public String getTypequnt() {
		return typequnt;
	}

	public String getAmount() {
		return amount;
	}
	private ArrayList<Bill> data_list;
	  private ObservableList<Bill> data_list_b;
	  private Stage stage_list;

@FXML private TableView<Bill> table_list;
@FXML private TableColumn<Bill, Integer> Tnum;
@FXML private TableColumn<Bill, String> Tname;
@FXML private TableColumn<Bill, Integer> Tpurpirce;
@FXML private TableColumn<Bill, String> Ttype;
@FXML private TableColumn<Bill, Integer> Tqunt;

	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			getdata_purpricedetails();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tnum.setCellValueFactory(new PropertyValueFactory<>("count"));
		Tname.setCellValueFactory(new PropertyValueFactory<>("detname"));
		Tpurpirce.setCellValueFactory(new PropertyValueFactory<>("detpurprice"));
		Ttype.setCellValueFactory(new PropertyValueFactory<>("det_typequnt"));
		Tqunt.setCellValueFactory(new PropertyValueFactory<>("datqunt"));
		table_list.setItems(data_list_b);
		table_action() ;
	}
public void setStage_list(Stage stage_list) {
		this.stage_list = stage_list;
	}
	
	private void getdata_purpricedetails() throws ClassNotFoundException, SQLException {
		data_list=new ArrayList<Bill>();
		int counter=1;
		Connection con =ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from purpriceorderdetails ");
		while(rs.next()) {
			if(rs.getString(1)==null) {
			Bill B =new Bill();
			B.setCount(counter);
			B.setPur_id(Integer.parseInt(rs.getString(2)));
			B.setDetname(rs.getString(4));
			B.setDetpurprice(Integer.parseInt(rs.getString(5)));
			B.setDet_typequnt(rs.getString(6));
			B.setDatqunt(Integer.parseInt(rs.getString(7)));
			data_list.add(B);
			counter++;
			}
		}
		data_list_b=FXCollections.observableArrayList(data_list);
		rs.close();
		stmt.close();
		con.close();
		
		
	}
	private void table_action() {
		table_list.setOnMouseClicked(e->{
			Bill BL=table_list.getItems().get(table_list.getSelectionModel().getSelectedIndex());
			name=BL.getDetname();
			purprice=String.valueOf(BL.getDetpurprice());
			typequnt=BL.getDet_typequnt();
			amount=String.valueOf(BL.getDatqunt());
		});
	}
@FXML private void onselectedlist () {
	
	stage_list.close();
}

}
