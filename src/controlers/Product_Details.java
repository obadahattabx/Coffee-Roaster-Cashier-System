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
import javafx.event.ActionEvent;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Ordersale;
import model.ProductsDetails;
import controlers.Edit_Details;

public class Product_Details implements Initializable {
	private ArrayList<ProductsDetails> data_bsd;
	private ObservableList<ProductsDetails> data_bsd_b;
	private Edit_Details ED;
	private String barcode;
	private String nameprod;
	private double saleprice;
	private double qunt;
	private String type;
	private double sale;
	private double discount;
	private double finalsale;
	private int pid;
	private Button b;
@FXML private TextField bfinalsale;	
@FXML private TextField text_num;
@FXML private TextField text_desc;
@FXML private TextField text_user;
@FXML private TextField text_company;
@FXML private TextField text_time;
@FXML private TextField text_date;
@FXML private TableView<ProductsDetails> table_bills;
@FXML private TableColumn<ProductsDetails, Integer> Tnum;
@FXML private TableColumn<ProductsDetails, Integer> Tsaleprice;
@FXML private TableColumn<ProductsDetails, Integer> Tqunt;
@FXML private TableColumn<ProductsDetails, Integer> Tsale;
@FXML private TableColumn<ProductsDetails, Integer> Tdiscount;
@FXML private TableColumn<ProductsDetails, Integer> Tfinalsale;
@FXML private TableColumn<ProductsDetails, String> Tbarcode;
@FXML private TableColumn<ProductsDetails, String> Tname;
@FXML private TableColumn<ProductsDetails, String> Ttype;
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	Tnum.setCellValueFactory(new PropertyValueFactory<>("count"));
	Tsaleprice.setCellValueFactory(new PropertyValueFactory<>("saleprice"));
	Tqunt.setCellValueFactory(new PropertyValueFactory<>("qunt"));
	Tsale.setCellValueFactory(new PropertyValueFactory<>("sale"));
	Tdiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
	Tname.setCellValueFactory(new PropertyValueFactory<>("pname"));
	Tbarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
	Ttype.setCellValueFactory(new PropertyValueFactory<>("typequnt"));
	Tfinalsale.setCellValueFactory(new PropertyValueFactory<>("finalsale"));
	table_action();

}

public void getdateDetails(int sale_id) throws ClassNotFoundException, SQLException {
	int count=1;
	double sum=0;
	data_bsd=new ArrayList<ProductsDetails>();
	Connection con =ConnectionDB.connectDB();
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select * from salepriceorderdetails where sale_id="+sale_id+"");
	while(rs.next()) {
		ProductsDetails pd=new ProductsDetails();
		pd.setCount(count);
		pd.setPid(Integer.parseInt(rs.getString(1)));
		pd.setBarcode(rs.getString(3));
		pd.setPname(rs.getString(4));
		pd.setSaleprice(Double.valueOf((rs.getString(5))));
		pd.setTypequnt(rs.getString(6));
		pd.setQunt(Double.valueOf(rs.getString(7)));
		pd.setSale(Double.valueOf(rs.getString(8)));
		pd.setDiscount(Double.valueOf(rs.getString(9)));
		pd.setFinalsale(Double.valueOf(rs.getString(10)));
		sum+=Double.valueOf(rs.getString(10));
		data_bsd.add(pd);
		count++;
		
	}
	data_bsd_b=FXCollections.observableArrayList(data_bsd);
	bfinalsale.setText(String.valueOf(sum));
	table_bills.setItems(data_bsd_b);
}

public void setText_num(String text_num) {
	this.text_num.setText(text_num);
}
public void setText_desc(String text_desc) {
	this.text_desc.setText(text_desc);
}
public void setText_user(String text_user) {
	this.text_user.setText(text_user);
}
public void setText_company(String text_company) {
	this.text_company.setText(text_company);
}
public void setText_time(String text_time) {
	this.text_time.setText(text_time);
}
public void setText_date(String text_date) {
	this.text_date.setText(text_date);
}

public void setB(Button b) {
	this.b = b;
}
private void table_action() {
	table_bills.setOnMouseClicked(e->{
		ProductsDetails PQ=table_bills.getItems().get
				(table_bills.getSelectionModel().getSelectedIndex());
		barcode=PQ.getBarcode();
		nameprod=PQ.getPname();
		saleprice=PQ.getSaleprice();
		sale=PQ.getSale();
		type=PQ.getTypequnt();
		qunt=PQ.getQunt();
		discount=PQ.getDiscount();
		finalsale=PQ.getFinalsale();
		pid=PQ.getPid();
		
		
	});
	
}
@FXML private void onEditDetails() throws IOException {
	FXMLLoader load= new FXMLLoader(getClass().getResource("/interfaces/EditDetails.fxml"));
	Parent screenEdit=load.load();
	 ED=load.getController();
	ED.setTextFiled(barcode, nameprod,String.valueOf( saleprice),
			String.valueOf( qunt), type,String.valueOf( sale),String.valueOf( discount),String.valueOf( finalsale));
	
	Scene sc= new Scene(screenEdit);
	Stage 	stage_Edit=new Stage();
	stage_Edit.setScene(sc);
	stage_Edit.initModality(Modality.APPLICATION_MODAL);
	Image ime= new Image("/icon/almkhtar.png");	
	stage_Edit.getIcons().add(ime);
	stage_Edit.show();
	
	ED.getSave().setOnAction(e1->{
		try {
			update_data();
			getdateDetails(Integer.parseInt(text_num.getText()))  ;
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		stage_Edit.close();
	});
}


private void update_data() throws ClassNotFoundException, SQLException {
	Connection con =ConnectionDB.connectDB();
	PreparedStatement pstmt=con.prepareStatement("update salepriceorderdetails set detbarcode='"+(ED.getBarcode().getText())+"',detname='"+ED.getName().getText()+"',detsaleprice="+Double.valueOf(ED.getSaleprice().getText())+","
			+ "det_typequnt='"+ED.getType().getText()+"',detqunt="+ Double.valueOf(ED.getQunt().getText())+","
					+ "detsale="+ Double.valueOf(ED.getSale().getText())+",detdiscount="+Double.valueOf(ED.getDiscount().getText())+","
							+ "detfinalsale="+Double.valueOf(ED.getFinalsale().getText())+" where  p_id ="+pid+" and sale_id ="+Integer.parseInt(text_num.getText())+" and detbarcode='"+barcode+"' and detqunt="+qunt+" and "
									+ "detsale="+sale+" and detdiscount="+discount+" and detfinalsale="+finalsale+" ;" );
	pstmt.executeUpdate();
	pstmt.close();
	con.close();
}
@FXML private void Delete() throws ClassNotFoundException, SQLException {
		Connection con =ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("delete from salepriceorderdetails  where  p_id ="+pid+" and sale_id ="+Integer.parseInt(text_num.getText())+" and detbarcode='"+barcode+"' and detqunt="+qunt+" and "
										+ "detsale="+sale+" and detdiscount="+discount+" and detfinalsale="+finalsale+" ;" );
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		getdateDetails(Integer.parseInt(text_num.getText()))  ;
	}


}
