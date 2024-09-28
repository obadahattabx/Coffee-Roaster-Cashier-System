package controlers;


import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Ordersale;
import model.ProductWithQuntitiy;
public class BillsPure_order implements Initializable {
	

	
		
		private String pureid;
		private int cust_id;
		private String puredec;
		private String pureuseradd;
		private  String pureaddtime;
		private String puredate;
		private String nameSup;
		private ArrayList<Ordersale> data_sale;
		private ObservableList<Ordersale>data_sale_b;
		
		
		
	@FXML private TableView<Ordersale> table_bills;
	@FXML private TableColumn<Ordersale, Integer> Tnum;
	@FXML private TableColumn<Ordersale, String> Tdesc;
	@FXML private TableColumn<Ordersale, String> Tnamesup;
	@FXML private TableColumn<Ordersale, String> Tuseradd;
	@FXML private TableColumn<Ordersale, String> Ttime;
	@FXML private TableColumn<Ordersale, String> Tdate;
	@FXML private Button Bdetails;
	@FXML private TextField text_date1;
	@FXML private TextField text_date2;
	@FXML private TextField text_time1;
	@FXML private TextField text_time2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Tnum.setCellValueFactory(new PropertyValueFactory<>("saleid"));
		Tdesc.setCellValueFactory(new PropertyValueFactory<>("saledesc"));
		Tnamesup.setCellValueFactory(new PropertyValueFactory<>("nameSup"));
		Tuseradd.setCellValueFactory(new PropertyValueFactory<>("saleuseradd"));
		Ttime.setCellValueFactory(new PropertyValueFactory<>("saleaddtime"));
		Tdate.setCellValueFactory(new PropertyValueFactory<>("saledate"));
		
		try {
			getdata() ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		table_bills.setItems(data_sale_b);
		table_action();
		
	}
	private void getdata() throws ClassNotFoundException, SQLException {
		data_sale=new ArrayList<Ordersale>();
		Connection con =ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select S.*,C.suppcompany from purpriceorder S,suppliers C where S.sup_id=C.supid; ");
		while (rs.next()) {
			Ordersale os=new Ordersale();
			os.setSaleid(Integer.parseInt(rs.getString(1)));
			os.setCust_id(Integer.parseInt(rs.getString(2)));
			os.setSaledate(rs.getString(3));
			os.setSaledec(rs.getString(4));
			os.setSaleuseradd(rs.getString(5));
			os.setSaleaddtime(rs.getString(6));
			os.setNameSup(rs.getString(7));
			data_sale.add(os);
		}
		data_sale_b=FXCollections.observableArrayList(data_sale);
		
		
		
	}


	private void table_action() {
		table_bills.setOnMouseClicked(e->{
			Ordersale PQ=table_bills.getItems().get
					(table_bills.getSelectionModel().getSelectedIndex());
		pureid=String.valueOf(PQ.getSaleid());
		puredate=PQ.getSaledate();
		puredec=PQ.getSaledec();
		pureaddtime=PQ.getSaleaddtime();
		pureuseradd=PQ.getSaleuseradd();
		nameSup=PQ.getNameSup();
		

			
		});
		
	}
	@FXML private void onDetails() throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
	FXMLLoader loader=new  FXMLLoader(getClass().getResource("/interfaces/BillsPureDetails.fxml"))	;
	Parent P=(Parent)loader.load();
	Products_DetailsP prod_dp=loader.getController();
	prod_dp.setText_desc(puredec);
	prod_dp.setText_company(nameSup);
	prod_dp.setText_date(puredate);
	prod_dp.setText_num(pureid);
	prod_dp.setText_time(pureaddtime);
	prod_dp.setText_user(pureuseradd);
	prod_dp.getdateDetails(Integer.parseInt(pureid));
	Scene sc=new Scene(P);
	Stage stage=new Stage();
	stage.setScene(sc);
	stage.initModality(Modality.APPLICATION_MODAL);
	Image ime= new Image("/icon/almkhtar.png");	
	stage.getIcons().add(ime);
	stage.show();


	}
	@FXML private void onDelete() throws ClassNotFoundException, SQLException{
		Connection con =ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("delete from purpriceorder where purid="+pureid+";");
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
		getdata();
		table_bills.setItems(data_sale_b);
	}
	@FXML private void onSearch() throws ClassNotFoundException, SQLException {
		String SQL;
		if(!text_date1.getText().equals("0000-00-00")&&!text_date2.getText().equals("0000-00-00")&&!text_time1.getText().equals("00:00:00")&&!text_time2.getText().equals("00:00:00")) {
			SQL="select S.*,C.suppcompany from purpriceorder S,suppliers C where S.sup_id=C.supid and purdate>='"+text_date1.getText()+"' and purdate<='"+text_date2.getText()+"' and puraddtime>='"+text_time1.getText()+"' and puraddtime<='"+text_time2.getText()+"' ; ";
			getdata_Search(SQL);
			table_bills.setItems(data_sale_b);
			
		}
		else if(!text_time1.getText().equals("00:00:00")&&!text_time2.getText().equals("00:00:00")) {
			SQL="select S.*,C.suppcompany from purpriceorder S,suppliers C where S.sup_id=C.supid and puraddtime>='"+text_time1.getText()+"' and puraddtime<='"+text_time2.getText()+"' ; ";
			getdata_Search(SQL);
			table_bills.setItems(data_sale_b);
			
		}
		else if (!text_date1.getText().equals("0000-00-00")&& !text_date2.getText().equals("0000-00-00")) {
			SQL="select S.*,C.suppcompany from purpriceorder S,suppliers C where S.sup_id=C.supid and purdate>='"+text_date1.getText()+"' and purdate<='"+text_date2.getText()+"'  ; ";
			getdata_Search(SQL);
			table_bills.setItems(data_sale_b);
			
		}
		else {
			getdata();
			table_bills.setItems(data_sale_b);
		}
		
		
	}
	private void getdata_Search(String SQL) throws ClassNotFoundException, SQLException {
		data_sale=new ArrayList<Ordersale>();
		Connection con =ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(SQL);
		while (rs.next()) {
			Ordersale os=new Ordersale();
			os.setSaleid(Integer.parseInt(rs.getString(1)));
			os.setCust_id(Integer.parseInt(rs.getString(2)));
			os.setSaledate(rs.getString(3));
			os.setSaledec(rs.getString(4));
			os.setSaleuseradd(rs.getString(5));
			os.setSaleaddtime(rs.getString(6));
			os.setNameSup(rs.getString(7));
			data_sale.add(os);
		}
		data_sale_b=FXCollections.observableArrayList(data_sale);
		
		
		
	}

}
