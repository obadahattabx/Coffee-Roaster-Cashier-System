package controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.xml.internal.stream.buffer.sax.Properties;

import application.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProductWithQuntitiy;
import model.ProductsDetails;
import model.Quntity;
public class Home_updateordelete implements Initializable {
	
	      private ArrayList<ProductWithQuntitiy> data_prodcuts;
	      private ObservableList<ProductWithQuntitiy> data_products_b;
	      private String selectedondeleted;
	      private String selectedbarcode;
	      private String selectedpurprice;
	      private String selectedsaleprice;
	      private String selecteddiscount;
	      private String selectedtype;
	      private String selectedqunt;
	     
	      private Home_update_Edit HE;
	      private String pid;
	      private Stage stage_Edit;
	      private Button But;
	      
	      

	@FXML private TableView<ProductWithQuntitiy> table_update;
	@FXML private TableColumn<ProductWithQuntitiy, Integer> Tnum;
	@FXML private TableColumn<ProductWithQuntitiy, String> Tname;
	@FXML private TableColumn<ProductWithQuntitiy, String> Tbarcode;
	@FXML private TableColumn<ProductWithQuntitiy, Integer> Tpurprice ;
	@FXML private TableColumn<ProductWithQuntitiy, Integer> Tsaleprice;
	@FXML private TableColumn<ProductWithQuntitiy, Integer> Tdiscount;
	@FXML private TableColumn<ProductWithQuntitiy, String> Tdate;
	@FXML private TableColumn<ProductWithQuntitiy, String> Ttype;
	@FXML private TableColumn<ProductWithQuntitiy, Integer> Tquntity;
	@FXML private TextField SearcBox;
	



	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			getdata_preoducts();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tnum.setCellValueFactory(new PropertyValueFactory<>("pid"));
		Tname.setCellValueFactory(new PropertyValueFactory<>("pname"));
		Tbarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
		Tpurprice.setCellValueFactory(new PropertyValueFactory<>("purprice"));
		Tsaleprice.setCellValueFactory(new PropertyValueFactory<>("saleprice"));
		Tdiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
		Tdate.setCellValueFactory(new PropertyValueFactory<>("pdate"));
		Ttype.setCellValueFactory(new PropertyValueFactory<>("qtype"));
		Tquntity.setCellValueFactory(new PropertyValueFactory<>("quntity"));
		table_update.setItems(data_products_b);
		table_action();
		
	}
	private void getdata_preoducts() throws ClassNotFoundException, SQLException {
		data_prodcuts =new ArrayList<ProductWithQuntitiy>();
		Connection con = ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from products ;");
		while (rs.next()){
			
			data_prodcuts.add(new ProductWithQuntitiy(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Double.valueOf(rs.getString(4)),
					Double.valueOf(rs.getString(5)),Double.valueOf(rs.getString(6)),
					rs.getString(7),Double.valueOf(rs.getString(8)),rs.getString(9),Integer.parseInt(rs.getString(10))));
					
			
		}
		data_products_b=FXCollections.observableArrayList(data_prodcuts);
		
		rs.close();
		stmt.close();
		con.close();
		
		
	}
	private void table_action() {
		table_update.setOnMouseClicked(e->{
			ProductWithQuntitiy PQ=table_update.getItems().get
					(table_update.getSelectionModel().getSelectedIndex());
			selectedondeleted=PQ.getPname();
			selectedbarcode=PQ.getBarcode();
			selectedpurprice=String.valueOf(PQ.getPurprice());
			selectedsaleprice=String.valueOf(PQ.getSaleprice());
			selecteddiscount=String.valueOf(PQ.getDiscount());
			selectedtype=PQ.getQtype();
			selectedqunt=String.valueOf(PQ.getQuntity());
			pid=String.valueOf(PQ.getPid());
			
			
			
		});
	}
	
	@FXML private void ondelete() throws ClassNotFoundException, SQLException {
		Connection con = ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("delete from products where pname='"+selectedondeleted+"';");
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		getdata_preoducts();
		table_update.setItems(data_products_b);
	}
	@FXML private void onEdit(ActionEvent e) throws IOException {
		FXMLLoader load= new FXMLLoader(getClass().getResource("/interfaces/Edit.fxml"));
		Parent screenEdit=load.load();
		 HE=load.getController();
		HE.setvaluesforEdit(selectedondeleted, selectedbarcode,selectedpurprice,
				selectedsaleprice, selecteddiscount,selectedtype,selectedqunt,pid);
		Scene sc= new Scene(screenEdit);
		stage_Edit=new Stage();
		stage_Edit.setScene(sc);
		stage_Edit.initModality(Modality.APPLICATION_MODAL);
		Image ime= new Image("/icon/almkhtar.png");	
		stage_Edit.getIcons().add(ime);
		stage_Edit.show();
		But=HE.getSave();
		But.setOnAction(e1->{
			try {
				update_data();
				getdata_preoducts() ;
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			table_update.setItems(data_products_b);
			stage_Edit.close();
		});

}
@FXML private void onSearch() {
		FilteredList<ProductWithQuntitiy>  filterdata= new FilteredList<>(data_products_b,p->true);
		SearcBox.textProperty().addListener((obsevable,oldvalue,newvalue)->{
			filterdata.setPredicate(pers->{
				if(newvalue==null||newvalue.isEmpty()) {
					return true;
				}
				String typedText=newvalue.toLowerCase();
				if(pers.getPname().toLowerCase().indexOf(typedText) != -1) {
					
					return true;
					
				}
				if(pers.getBarcode().toLowerCase().indexOf(typedText) != -1) {
					
					return true;
					
				}
			
				
				return false; 
			});
			SortedList<ProductWithQuntitiy> sortedlist=new SortedList<>(filterdata);
			sortedlist.comparatorProperty().bind(table_update.comparatorProperty());
			table_update.setItems(sortedlist);
			
			
		});
	}
	private void update_data() throws ClassNotFoundException, SQLException {
		Connection con =ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("update products set pid="+Integer.parseInt(HE.getPid().getText())+",barcode='"+HE.getEbarcode().getText()+"',pname='"+HE.getEname().getText()+"',"
				+ "purprice="+ Double.valueOf(HE.getEpurprice().getText())+",saleprice="+ Double.valueOf(HE.getEsalePrice().getText())+","
						+ "discount="+ Double.valueOf(HE.getEdiscount().getText())+",ptype='"+HE.getEtype().getText()+"',"
								+ "pamount="+Double.valueOf(HE.getEqunt().getText())+" where pid ="+Integer.parseInt(pid)+"; " );
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
}