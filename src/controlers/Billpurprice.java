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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bill;

public class Billpurprice implements Initializable {
	
      private int pid;	
      private JustInputInteger checknum=new JustInputInteger();
	  private String Selected_type="";
@FXML private RadioButton kgQunt;	
@FXML private RadioButton Gqunt;
@FXML private TextField numberbill;
@FXML private TextField userprog;
@FXML private TextField datebill;
@FXML private TextField text_name;
@FXML private TextField text_purprice ;
@FXML private TextField text_type;
@FXML private MenuButton mbuttype;
@FXML private TextField sname;
@FXML private TextField sphone ;
@FXML private TextField scompany;
@FXML private TextField text_finalpur;
@FXML private RadioButton rwithoutsup;
@FXML private RadioButton raddsup;
@FXML private RadioButton radd;
@FXML private RadioButton rnotadd;
@FXML private TextField SumFinalpur;

	  private ArrayList<Bill> data_bill;
	  private ObservableList<Bill> data_bill_B;
	  private Date_pc datepc;
	  private int counter=1;
	  
@FXML private TableView<Bill> table_bill;
@FXML private TableColumn<Bill, String> bname;
@FXML private TableColumn<Bill, String> barcode;
@FXML private TableColumn<Bill, String> btype;
@FXML private TableColumn<Bill, Double> bnum;
@FXML private TableColumn<Bill, Double> bqunt;
@FXML private TableColumn<Bill, Double> bpurprice;
@FXML private TableColumn<Bill, Double> bfinalpur;
@FXML private TextArea descBill; 




private void Alert(String s) {
 	Alert alert =new Alert(AlertType.WARNING);
  alert.setTitle("Â‰«ﬂ Œÿ√");
  alert.setHeaderText(null);
  alert.setContentText(s);
  alert.showAndWait();
  
}




@FXML private void  addBillfortable() throws ClassNotFoundException, SQLException {
	
	double sum=0;
	Bill B= new Bill();
	if(text_name.getText().isEmpty()||text_purprice.getText().isEmpty()||
			text_type.getText().isEmpty()||text_finalpur.getText().isEmpty()) {
		
	Alert(" √ﬂœ „‰  ⁄»∆… «·Ã„Ì⁄ ....Û");
	
	return;
	}
	 if(Selected_type=="") {
		Alert("—Ã«¡  ≈œŒ«· ‰Ê⁄ ﬂ„Ì… ");
		return;
	}
	 if(radd.isSelected()) {
		searchpid(text_name.getText());
		B.setP_id(pid);
		
	if(B.getP_id()==0) {
		Alert("Â–« ’‰› €Ì— „÷«› „”»ﬁ«");
		return;
	}
	}
	
	
	
	B.setCount(counter);
	B.setDetname(text_name.getText());
	B.setDetpurprice(Double.valueOf(text_purprice.getText()));
	B.setDet_typequnt(Selected_type);
	B.setDatqunt(Double.valueOf(text_type.getText()));
	B.setFinalpure(Double.valueOf(text_finalpur.getText()));
	counter++;
	data_bill.add(B);
	data_bill_B =FXCollections.observableArrayList(data_bill);
	table_bill.setItems(data_bill_B);
	sum+=Double.valueOf(text_finalpur.getText());
	restdetails();
	SumFinalpur.setText(String.valueOf(sum));
	
}
private void get_final() {
	double num;
	if(Selected_type=="Ê“‰  = ﬂ€„") {
		num=Double.valueOf(text_purprice.getText())*Double.valueOf(text_type.getText());
		text_finalpur.setText(String.valueOf(num));
		
	}
	else if (Selected_type=="Ê“‰  = €—«„") {
		num=(Double.valueOf(text_purprice.getText())/1000)*Double.valueOf(text_type.getText());
		text_finalpur.setText(String.valueOf(num));
		
	}
	else {
		num=Double.valueOf(text_purprice.getText())*Double.valueOf(text_type.getText());
		text_finalpur.setText(String.valueOf(num));
		
	}
}
@FXML private void Weight() {
	kgQunt.setVisible(true);
	Gqunt.setVisible(true);
	get_final();
	kgQunt.setOnAction(e->{
		mbuttype.setText("Ê“‰  = ﬂ€„");
		Selected_type="Ê“‰  = ﬂ€„";
		get_final();
		
		
	});
	Gqunt.setOnAction(e->{
		mbuttype.setText("Ê“‰  = €—«„");
		Selected_type="Ê“‰  = €—«„" ;
		get_final();
		
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
	mbuttype.setText("‰Ê⁄ ﬂ„Ì… :Õ»…");

}
@FXML private void saveBill() throws ClassNotFoundException, SQLException  {
	
	
	if(numberbill.getText().isEmpty()) {
		Alert("—Ã«¡ «÷«›… —ﬁ„ ··›« Ê—…");
		return;
	}
	if(data_bill.size()==0) {
		Alert("⁄–—« !  ·«ÌÊÃœ «Ì «÷«›…...Û");
		return;
	}
	if(Checkpurid(Integer.parseInt(numberbill.getText()))) {
		Alert("ÌÊÃœ ›« Ê—… »‰›” —ﬁ„   ... —Ã«¡ «œŒ«·  €Ì— –·ﬂ");
		return;
	}
	addorderintosql();
	Billdetails();
	restall() ;
	data_bill=new ArrayList<Bill>();
	data_bill_B =FXCollections.observableArrayList(data_bill);
	table_bill.setItems(data_bill_B);
	counter=1;

}
private int addSup() throws ClassNotFoundException, SQLException {
	Connection con= ConnectionDB.connectDB();
	PreparedStatement pstmt;
	Statement tsmt;
	ResultSet rs1;
	int sup_id=0;
	if(rwithoutsup.isSelected()) {
	
		
		 pstmt=con.prepareStatement("Insert into suppliers  (suppcompany)  values ('·« ÌÊÃœ «”„')");
		 pstmt.executeUpdate();
		 pstmt.close();
		  tsmt=con.createStatement();
		  rs1=tsmt.executeQuery("select max(supid) from suppliers;");
		  while(rs1.next()) {
			  sup_id=Integer.parseInt(rs1.getString(1));
		  }
		  return sup_id;
	}
	return 0;

}
private void addorderintosql() throws ClassNotFoundException, SQLException {
	 datepc =new Date_pc();
	Connection con = ConnectionDB.connectDB();
	PreparedStatement pstmt;
	int sup_id=addSup() ;
	pstmt= con.prepareStatement("insert into purpriceorder (purid,sup_id,purdate,purdesc,puruseradd,puraddtime) values"
			+ "("+Integer.parseInt(numberbill.getText())+","+sup_id+",'"+datepc.getdate()+"',"
					+ "'"+descBill.getText()+"','"+userprog.getText()+"','"+datepc.getTime()+"'); ");
	pstmt.executeUpdate();
	pstmt.close();
	con.close();
}
private void Billdetails() throws ClassNotFoundException, SQLException {
	int x=0;
	Connection con = ConnectionDB.connectDB();
	PreparedStatement pstmt;
	if(radd.isSelected()) {
		
	pstmt= con.prepareStatement("insert into purpriceorderdetails (p_id,pur_id,detname,detpurprice,det_typequnt,datqunt,finalpur) values"
			+ "("+data_bill.get(x).getP_id()+","+Integer.parseInt(numberbill.getText())+",'"+data_bill.get(x).getDetname()+"',"
					+ ""+data_bill.get(x).getDetpurprice()+",'"+data_bill.get(x).getDet_typequnt()+"',"
							+ ""+data_bill.get(x).getDatqunt() +","+data_bill.get(x).getFinalpure()+"); ");
	
	pstmt.executeUpdate();
	update_after_add(data_bill.get(x).getDetpurprice(),data_bill.get(x).getDatqunt(),data_bill.get(x).getDetname());
	x++;
	while(data_bill.size()!=x) {
	pstmt= con.prepareStatement("insert into purpriceorderdetails (p_id,pur_id,detname,detpurprice,det_typequnt,datqunt,finalpur) values"
			+ "("+data_bill.get(x).getP_id()+","+Integer.parseInt(numberbill.getText())+",'"+data_bill.get(x).getDetname()+"',"
					+ ""+data_bill.get(x).getDetpurprice()+",'"+data_bill.get(x).getDet_typequnt()+"',"
							+ ""+data_bill.get(x).getDatqunt() +","+data_bill.get(x).getFinalpure()+"); ");
	
	
	pstmt.executeUpdate();
	update_after_add(data_bill.get(x).getDetpurprice(),data_bill.get(x).getDatqunt(),data_bill.get(x).getDetname());
	x++;
	}

	pstmt.close();
	con.close();
	}
	else {
		
			
			pstmt= con.prepareStatement("insert into purpriceorderdetails (pur_id,detname,detpurprice,det_typequnt,datqunt,finalpur) values"
					+ "("+Integer.parseInt(numberbill.getText())+",'"+data_bill.get(x).getDetname()+"',"
							+ ""+data_bill.get(x).getDetpurprice()+",'"+data_bill.get(x).getDet_typequnt()+"',"
									+ ""+data_bill.get(x).getDatqunt() +","+data_bill.get(x).getFinalpure()+"); ");
			x++;
			pstmt.executeUpdate();
			while(data_bill.size()!=x) {
			pstmt= con.prepareStatement("insert into purpriceorderdetails (pur_id,detname,detpurprice,det_typequnt,datqunt,finalpur) values"
					+ "("+Integer.parseInt(numberbill.getText())+",'"+data_bill.get(x).getDetname()+"',"
							+ ""+data_bill.get(x).getDetpurprice()+",'"+data_bill.get(x).getDet_typequnt()+"',"
									+ ""+data_bill.get(x).getDatqunt() +","+data_bill.get(x).getFinalpure()+"); ");
			x++;
			pstmt.executeUpdate();
			}

			pstmt.close();
			con.close();
			
	}
}
private void searchpid(String name) throws ClassNotFoundException, SQLException {
	
	Connection con = ConnectionDB.connectDB();
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select pid from products where pname='"+name+"'");
	while(rs.next()) {
		pid=Integer.parseInt(rs.getString(1));
	}
	
	rs.close();
	stmt.close();
	con.close();
	
}
private boolean Checkpurid(int num) throws ClassNotFoundException, SQLException {
	int Pur=-1;
	Connection con = ConnectionDB.connectDB();
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select purid from purpriceorder where purid="+num+"");
	while(rs.next()) {
		Pur=Integer.parseInt(rs.getString(1));
	}
	
	rs.close();
	stmt.close();
	con.close();
	if(Pur==-1) {
		return false;
	}
	else {
		return true;
	}
	
}
private void update_after_add(double pur,double amount,String name) throws ClassNotFoundException, SQLException {
	Connection con = ConnectionDB.connectDB();
	PreparedStatement pstmt=con.prepareStatement("update products set purprice="+pur+",pamount=pamount+"+amount+" where pname='"+name+"' ");
	pstmt.executeUpdate();
	pstmt.close();
	con.close();
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
private void restdetails() {
	text_name.setText("");
	text_purprice.setText("");
	text_type.setText("");
	text_finalpur.setText("");
	Selected_type="";
}
private void restall() {
	numberbill.setText("");
	userprog.setText("");
	descBill.setText("");
	restdetails();
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	checknum.check(numberbill);
	checknum.check(text_purprice);
	checknum.check(text_type);
	userprog.setText(UserAdd.getNameadduser());
	
	data_bill=new ArrayList<Bill>();
	bname.setCellValueFactory(new PropertyValueFactory<>("detname"));
	bnum.setCellValueFactory(new PropertyValueFactory<>("count"));
	btype.setCellValueFactory(new PropertyValueFactory<>("det_typequnt"));
	bqunt.setCellValueFactory(new PropertyValueFactory<>("datqunt"));
	bpurprice.setCellValueFactory(new PropertyValueFactory<>("detpurprice"));
	bfinalpur.setCellValueFactory(new PropertyValueFactory<>("finalpure"));
	datepc=new Date_pc();
	datebill.setText(datepc.getdate());
	if(Selected_type!="") {
	text_type.setOnKeyReleased(e->{
		get_final();
	});
	}
	
}

}
