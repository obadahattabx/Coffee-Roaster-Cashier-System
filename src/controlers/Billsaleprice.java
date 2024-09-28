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
import application.Date_pc;
import application.JustInputInteger;
import application.UserAdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Billsale;

public class Billsaleprice implements Initializable{

	      private String Selected_type="";
	      private ArrayList<Billsale> data_bs;
	      private ObservableList<Billsale> data_bs_b;
	      private int count=1;
	      private int pid;
	      private JustInputInteger checknum=new JustInputInteger();
	@FXML private RadioButton kgQunt;	
	@FXML private RadioButton Gqunt;
	@FXML private TextField numberbill;
	@FXML private TextField userprog;
	@FXML private TextField datebill;
	@FXML private TextArea descBill;
	@FXML private RadioButton rwithoutsup;
	@FXML private RadioButton raddsup;
	
	@FXML private TextField text_name;
	@FXML private TextField text_saleprice ;
	@FXML private TextField text_type;
	@FXML private TextField text_barcode;
	@FXML private TextField text_sale;
	@FXML private TextField text_discount;
	@FXML private TextField text_finalsale;
	@FXML private MenuButton mbuttype;
	@FXML private Button addsaleprice;
	@FXML private TextField sname;
	@FXML private TextField scompany;
	@FXML private TextField sphone;
	@FXML private TableView<Billsale> table_bill;
	@FXML private TableColumn<Billsale, Integer> bnum;
	@FXML private TableColumn<Billsale, String> barcode;
	@FXML private TableColumn<Billsale, String>bname;
	@FXML private TableColumn<Billsale, Double> bsaleprice;
	@FXML private TableColumn<Billsale, Double>bqunt;
	@FXML private TableColumn<Billsale, String>btype;
	@FXML private TableColumn<Billsale, Double>bsale;
	@FXML private TableColumn<Billsale, Double>bdiscount;
	@FXML private TableColumn<Billsale, Double>bfinalsale;
	@FXML private TextField finalsb;
	@FXML private RadioButton saleid;
	
	
	
	private void Alert(String s) {
	 	Alert alert =new Alert(AlertType.WARNING);
	  alert.setTitle("Â‰«ﬂ Œÿ√");
	  alert.setHeaderText(null);
	  alert.setContentText(s);
	  alert.showAndWait();
	  
}
	
	private void getdata_for_fill(String name) throws ClassNotFoundException, SQLException {
		Connection con=ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select pid,barcode,saleprice,discount from products where pname='"+name+"' ");
		while(rs.next()) {
			pid=Integer.parseInt(rs.getString(1));
			text_barcode.setText(rs.getString(2));
			text_saleprice.setText(rs.getString(3));
			text_discount.setText(rs.getString(4));
			
		}
		
		rs.close();
		stmt.close();
		con.close();
		
	}
	private void getdata_for_barcode(String name) throws ClassNotFoundException, SQLException {
		String bar=name;
		if(name.length()==13) {
		bar=bar.substring(0,7);
		Connection con=ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select pid,pname,saleprice,discount,ptype from products where pid='"+bar+"' ");
		while(rs.next()) {
			pid=Integer.parseInt(rs.getString(1));
			text_name.setText(rs.getString(2));
			text_saleprice.setText(rs.getString(3));
			text_discount.setText(rs.getString(4));
			Selected_type=rs.getString(5);
			
		}
		
		rs.close();
		stmt.close();
		con.close();
		text_type.setText(String.valueOf(getAmountofProducts(name)));
		text_finalsale.setText(String.valueOf(get_finalsale()));
		text_sale.setText(String.valueOf(get_Psale() ));
		
		}
	}
	private double getAmountofProducts(String name) {
		String name1=name.substring(7,13);
		double number=Double.valueOf(name1);
		number=number/1000;
		return number;
		
	}
	private void insertTable() {
		if(text_discount.getText().isEmpty()||text_name.getText().isEmpty()||text_saleprice.getText().isEmpty()||
				text_type.getText().isEmpty()) {
			
		Alert(" √ﬂœ „‰  ⁄»∆… «·Ã„Ì⁄ ....Û");
			
		}
		else if(Selected_type=="") {
			Alert("—Ã«¡  ≈œŒ«· ‰Ê⁄ ﬂ„Ì… ");
		}
		else {
		Billsale bs=new Billsale();
		bs.setP_id(pid);
		bs.setDetbarcode(text_barcode.getText());
		bs.setDetname(text_name.getText());
		bs.setDetsaleprice(Double.valueOf(text_saleprice.getText()));
		bs.setDet_typequnt(Selected_type);
		bs.setDetqunt(Double.valueOf(text_type.getText()));
		bs.setDetsale(Double.valueOf(text_sale.getText()));
		bs.setDetfinalsale(Double.valueOf(text_finalsale.getText()));
		bs.setDetdiscount(Double.valueOf(text_discount.getText()));
		bs.setCount(count);
		data_bs.add(bs);
		data_bs_b=FXCollections.observableArrayList(data_bs);
		table_bill.setItems(data_bs_b);
		count++;
		finalsb.setText(String.valueOf(updateFinal_sale()));
		restall();
	}
	}
	private void getIntotableForBarcode() throws ClassNotFoundException, SQLException {
		if(text_barcode.getText().length()==13) {
		getdata_for_barcode(text_barcode.getText());
		insertTable();
		
		text_barcode.setManaged(true);
		}
		
	}
	private double get_Psale() {
		double num;
		if(Selected_type=="Ê“‰  = ﬂ€„") {
			num=Double.valueOf(text_saleprice.getText())*Double.valueOf(text_type.getText());
			text_sale.setText(String.valueOf(num));
			return num;
		}
		else if (Selected_type=="Ê“‰  = €—«„") {
			num=(Double.valueOf(text_saleprice.getText())/1000)*Double.valueOf(text_type.getText());
			text_sale.setText(String.valueOf(num));
			return num;
		}
		else {
			num=Double.valueOf(text_saleprice.getText())*Double.valueOf(text_type.getText());
			text_sale.setText(String.valueOf(num));
			return num;
		}
	}
	private double get_finalsale() {
		double disc= Double.valueOf(text_discount.getText())/100;
		double sale=get_Psale();
	 double  finalsal= sale-(sale*disc);
	 return  finalsal;
	   
	}
	private double updateFinal_sale() {
		int x=0;
		double sum=0;
		while(data_bs.size()!=x){
			sum+=data_bs.get(x).getDetfinalsale();
			x++;
		}
		return sum;
	}
	private void Billdetails() throws ClassNotFoundException, SQLException {
		int x=0;
		Connection con = ConnectionDB.connectDB();
		PreparedStatement pstmt;
			
		pstmt= con.prepareStatement("insert into salepriceorderdetails (p_id,sale_id,detbarcode,detname,detsaleprice,det_typequnt,detqunt,detsale,detdiscount,detfinalsale) values"
				+ "("+data_bs.get(x).getP_id()+","+Integer.parseInt(numberbill.getText())+",'"+data_bs.get(x).getDetbarcode()+"','"+data_bs.get(x).getDetname()+"',"
						+ ""+data_bs.get(x).getDetsaleprice()+",'"+data_bs.get(x).getDet_typequnt()+"',"
								+ ""+data_bs.get(x).getDetqunt() +","+data_bs.get(x).getDetsale() +","+data_bs.get(x).getDetdiscount() +","+data_bs.get(x).getDetfinalsale() +"); ");
		
		pstmt.executeUpdate();
		update_after_add(data_bs.get(x).getDetqunt(),data_bs.get(x).getDetname());
		x++;
		while(data_bs.size()!=x) {
			pstmt= con.prepareStatement("insert into salepriceorderdetails (p_id,sale_id,detbarcode,detname,detsaleprice,det_typequnt,detqunt,detsale,detdiscount,detfinalsale) values"
					+ "("+data_bs.get(x).getP_id()+","+Integer.parseInt(numberbill.getText())+",'"+data_bs.get(x).getDetbarcode()+"','"+data_bs.get(x).getDetname()+"',"
							+ ""+data_bs.get(x).getDetsaleprice()+",'"+data_bs.get(x).getDet_typequnt()+"',"
									+ ""+data_bs.get(x).getDetqunt() +","+data_bs.get(x).getDetsale() +","+data_bs.get(x).getDetdiscount() +","+data_bs.get(x).getDetfinalsale() +"); ");
			
		
		pstmt.executeUpdate();
		update_after_add(data_bs.get(x).getDetqunt(),data_bs.get(x).getDetname());
		x++;
		}

		pstmt.close();
		con.close();
		
	}
	private void update_after_add(double amount,String name) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionDB.connectDB();
		PreparedStatement pstmt=con.prepareStatement("update products set pamount=pamount-"+amount+" where pname='"+name+"' ");
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	private int searchsaleid() throws ClassNotFoundException, SQLException {
		int sale_id=0;
		Connection con = ConnectionDB.connectDB();
		Statement tsmt;
		ResultSet rs1;
		 tsmt=con.createStatement();
		  rs1=tsmt.executeQuery("select max(saleid) from salepriceorder;");
		  while (rs1.next()) {
			  sale_id=Integer.parseInt(rs1.getString(1))+1; 
		  }
		  return sale_id;
		
		
	
		
	}

	
	private void restall() {
		text_name.setText("");
		text_barcode.setText("");
		text_discount.setText("");
		text_finalsale.setText("");
		text_sale.setText("");
		text_saleprice.setText("");
		text_type.setText("");
	}
	private void InsertIntoSqlOrder() throws ClassNotFoundException, SQLException {
		Date_pc dp=new Date_pc();
		Connection con= ConnectionDB.connectDB();
		PreparedStatement pstmt;
		int cust_id=CustIntoSql();
		
		 pstmt=con.prepareStatement("Insert into salepriceorder (saleid,cust_id,saledate,saledesc,saleuseradd,saleaddtime) values "
				+ "("+Integer.parseInt(numberbill.getText())+","+cust_id+",'"+datebill.getText()+"','"+descBill.getText()+"',"
						+ "'"+userprog.getText()+"','"+dp.getTime()+"');" );
		 pstmt.executeUpdate();
		 pstmt.close();
		
		
		
		con.close();
		
	}
	private int CustIntoSql() throws ClassNotFoundException, SQLException{
		Connection con= ConnectionDB.connectDB();
		PreparedStatement pstmt;
		Statement tsmt;
		ResultSet rs1;
		int custid=0;
		if(rwithoutsup.isSelected()) {
		
			
			 pstmt=con.prepareStatement("Insert into customer  (custcompany)  values ('·« ÌÊÃœ «”„')");
			 pstmt.executeUpdate();
			 pstmt.close();
			  tsmt=con.createStatement();
			  rs1=tsmt.executeQuery("select max(custid) from customer;");
			  while(rs1.next()) {
				  custid=Integer.parseInt(rs1.getString(1));
			  }
			  return custid;
		}
		return 0;
	}

	private void restallFull() {
		numberbill.setText("");
		userprog.setText("");
		descBill.setText("");
		finalsb.setText("");
		restall();
	}
	private boolean Checksaleid(int num) throws ClassNotFoundException, SQLException {
		int sale=-1;
		Connection con = ConnectionDB.connectDB();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select saleid from salepriceorder where saleid="+num+"");
		while(rs.next()) {
			sale=Integer.parseInt(rs.getString(1));
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		if(sale==-1) {
			return false;
		}
		else {
			return true;
		}
		
		
	}
	
	@FXML private void onsavebill() throws ClassNotFoundException, SQLException {
		
		if(numberbill.getText().isEmpty()) {
		Alert("Ì—ÃÏ ≈œŒ«·  —ﬁ„  «·›« Ê—… ");
		
		}
		else if(!saleid.isSelected()&&Checksaleid(Integer.parseInt(numberbill.getText()))) {
			Alert("ÌÊÃœ ›« Ê—… »‰›” —ﬁ„   ... —Ã«¡ «œŒ«·  €Ì— –·ﬂ");
			
		}
		else if (data_bs.size()==0) {
			Alert("⁄–—« !  ·«ÌÊÃœ «Ì «÷«›…...Û");
		}
		
		else {
		InsertIntoSqlOrder();
		Billdetails();
		restallFull() ;
		data_bs=new ArrayList<Billsale>();
		data_bs_b=FXCollections.observableArrayList(data_bs);
		count=1;
		table_bill.setItems(data_bs_b);
		if(saleid.isSelected()) {
			numberbill.setText(String.valueOf(searchsaleid()));
			}
		}
		
	
	}
	@FXML private void onNewBillsale() throws ClassNotFoundException, SQLException {
		data_bs=new ArrayList<Billsale>();
		data_bs_b=FXCollections.observableArrayList(data_bs);
		count=1;
		table_bill.setItems(data_bs_b);
		restallFull() ;
	}
	@FXML private void onaddsaleprice() {
		insertTable();
	}
	@FXML private void onsetname() throws ClassNotFoundException, SQLException {
		getdata_for_fill(text_name.getText());
		text_type.setOnKeyReleased(e->{
			text_finalsale.setText(String.valueOf(get_finalsale()));
		});
		text_discount.setOnKeyReleased(e->{
			text_finalsale.setText(String.valueOf(get_finalsale()));
		});
		
	}
	
	
	
	
	@FXML private void onwithoutsup() {
		sname.setDisable(true);
		sphone.setDisable(true);
		scompany.setDisable(true);
	}
	@FXML private void onaddsup() {
		sname.setDisable(false);
		sphone.setDisable(false);
		scompany.setDisable(false);
	}
	@FXML private void Weight() {
		kgQunt.setVisible(true);
		Gqunt.setVisible(true);
		kgQunt.setOnAction(e->{
			mbuttype.setText("Ê“‰  = ﬂ€„");
			Selected_type="Ê“‰  = ﬂ€„";
			text_finalsale.setText(String.valueOf(get_finalsale()));
			
		});
		Gqunt.setOnAction(e->{
			mbuttype.setText("Ê“‰  = €—«„");
			Selected_type="Ê“‰  = €—«„" ;
			text_finalsale.setText(String.valueOf(get_finalsale()));
		});
		if(kgQunt.isSelected()) {
		Selected_type="Ê“‰  = ﬂ€„";
		mbuttype.setText("Ê“‰  = ﬂ€„");
		
		}
		else {
		Selected_type="Ê“‰  = €—«„" ;
		mbuttype.setText("Ê“‰  = €—«„");
		}
	}
	@FXML private void item() {
		kgQunt.setVisible(false);
		Gqunt.setVisible(false);
		Selected_type="Õ»…";
		mbuttype.setText("Õ»…");
	}
	@FXML private void onSaleselected() throws ClassNotFoundException, SQLException{
		if(saleid.isSelected()) {
		numberbill.setText(String.valueOf(searchsaleid()));
		numberbill.setDisable(true);
		}
		else
		{
			numberbill.setDisable(false);	
			
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		checknum.check(text_discount);
		checknum.check(text_saleprice);
		checknum.check(text_type);
		checknum.check(numberbill);
		userprog.setText(UserAdd.getNameadduser());
		Date_pc dp=new Date_pc();
		data_bs=new ArrayList<Billsale>();
		datebill.setText(dp.getdate());
		bnum.setCellValueFactory(new PropertyValueFactory<>("count1"));
		bname.setCellValueFactory(new PropertyValueFactory<>("detname"));
		barcode.setCellValueFactory(new PropertyValueFactory<>("detbarcode"));
		bsaleprice.setCellValueFactory(new PropertyValueFactory<>("detsaleprice"));
		btype.setCellValueFactory(new PropertyValueFactory<>("det_typequnt"));
		bqunt.setCellValueFactory(new PropertyValueFactory<>("detqunt"));
		bsale.setCellValueFactory(new PropertyValueFactory<>("detsale"));
		bdiscount.setCellValueFactory(new PropertyValueFactory<>("detdiscount"));
		bfinalsale.setCellValueFactory(new PropertyValueFactory<>("detfinalsale"));
		text_barcode.setOnKeyReleased(e->{

			try {
				getIntotableForBarcode();
								
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		});
		
				
			
		
		
		
	}
	
	
	
	
}
