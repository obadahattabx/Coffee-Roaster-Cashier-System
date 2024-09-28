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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Bill;
import model.ProductsDetails;
import controlers.Edtit_DetailsP;
public class Products_DetailsP implements Initializable {
	
	private ArrayList<Bill> data_bsd;
	private ObservableList<Bill> data_bsd_b; 
	private Edtit_DetailsP ED;
	private String nameprod;
	private double purprice;
	private double qunt;
	private String type;

	private double finalsale;
	private int pid;
	@FXML private TextField bfinalsale;	
	@FXML private TextField text_num;
	@FXML private TextField text_desc;
	@FXML private TextField text_user;
	@FXML private TextField text_company;
	@FXML private TextField text_time;
	@FXML private TextField text_date;
	@FXML private TableView<Bill> table_bills;
	@FXML private TableColumn<Bill, Integer> Tnum;
	@FXML private TableColumn<Bill, Double> Tpureprice;
	@FXML private TableColumn<Bill, Double> Tqunt;
	@FXML private TableColumn<Bill, Double> Tfinalpure;
	@FXML private TableColumn<Bill, String> Tname;
	@FXML private TableColumn<Bill, String> Ttype;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Tnum.setCellValueFactory(new PropertyValueFactory<>("count"));
		Tpureprice.setCellValueFactory(new PropertyValueFactory<>("detpurprice"));
		Tqunt.setCellValueFactory(new PropertyValueFactory<>("datqunt"));
		Tname.setCellValueFactory(new PropertyValueFactory<>("detname"));
		Ttype.setCellValueFactory(new PropertyValueFactory<>("det_typequnt"));
		Tfinalpure.setCellValueFactory(new PropertyValueFactory<>("finalpure"));
		table_action();

	}
	
	private void table_action() {
		table_bills.setOnMouseClicked(e->{
			Bill PQ=table_bills.getItems().get
					(table_bills.getSelectionModel().getSelectedIndex());
			
			nameprod=PQ.getDetname();
			purprice=PQ.getDetpurprice();
			type=PQ.getDet_typequnt();
			qunt=PQ.getDatqunt();
			finalsale=PQ.getFinalpure();
			pid=PQ.getP_id();
			
			
	
			
		});
		
	}
	
	@FXML private void onEditDetails() throws IOException {
		FXMLLoader load= new FXMLLoader(getClass().getResource("/interfaces/EditDetailsP.fxml"));
		Parent screenEdit=load.load();
		 ED=load.getController();
		ED.setTextField(nameprod,String.valueOf(purprice),String.valueOf(qunt),type,String.valueOf(finalsale));
		
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
	public void getdateDetails(int pur_id) throws ClassNotFoundException, SQLException {
		int count=1;
		double sum=0;
		data_bsd=new ArrayList<Bill>();
		Connection con =ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from purpriceorderdetails where pur_id="+pur_id+"");
		while(rs.next()) {
			Bill pd=new Bill();
			pd.setCount(count);
			pd.setP_id(Integer.parseInt(rs.getString(2)));
			pd.setDetname(rs.getString(4));
			pd.setDetpurprice(Double.valueOf((rs.getString(5))));
			pd.setDet_typequnt(rs.getString(6));
			pd.setDatqunt(Double.valueOf(rs.getString(7)));
			pd.setFinalpure(Double.valueOf(rs.getString(8)));
			sum+=Double.valueOf(rs.getString(8));
			data_bsd.add(pd);
			count++;
			
		}
		data_bsd_b=FXCollections.observableArrayList(data_bsd);
		bfinalsale.setText(String.valueOf(sum));
		table_bills.setItems(data_bsd_b);
	}
	@FXML private void onDelete() throws ClassNotFoundException, SQLException {
		Connection con =ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("delete from purpriceorderdetails  where  p_id ="+pid+" and pur_id ="+Integer.parseInt(text_num.getText())+" and  datqunt="+qunt+" and "
										+ " finalpur="+finalsale+" ;" );
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		getdateDetails(Integer.parseInt(text_num.getText()))  ;
	}
	
	private void update_data() throws ClassNotFoundException, SQLException {
		Connection con =ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("update purpriceorderdetails set detname='"+ED.getName().getText()+"',detpurprice="+Double.valueOf(ED.getPurprice().getText())+","
				+ "det_typequnt='"+ED.getType().getText()+"',datqunt="+ Double.valueOf(ED.getQunt().getText())+","
								+ "finalpur="+Double.valueOf(ED.getFinalsale().getText())+" where  p_id ="+pid+" and pur_id ="+Integer.parseInt(text_num.getText())+" and detname='"+nameprod+"' and datqunt="+qunt+" and "
										+ "finalpur="+finalsale+" ;" );
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
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
}
