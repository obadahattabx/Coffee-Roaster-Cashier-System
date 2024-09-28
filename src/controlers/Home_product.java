package controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import application.ConnectionDB;
import application.Date_pc;
import application.JustInputInteger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Category;

public class Home_product implements Initializable {
	  private JustInputInteger checknum=new JustInputInteger();
	  private Listpurprice Lpurprice;
	  private String checkcategory="";
	  private boolean selectedpid =true;
	  private double qunt;
	  private   String Selected_type;
	  private  Home_category Hcategory;
	  private  Stage stage_category;
@FXML private   Label lab_choice;
@FXML private TextField text_name;
@FXML private TextField text_barcode;
@FXML private TextField text_purprice;
@FXML private TextField text_saleprice;
@FXML private TextField text_qunt;
@FXML private TextField text_discount;
@FXML private TextField text_date;
@FXML private RadioButton kgQunt;
@FXML private RadioButton Gqunt;
@FXML private  MenuItem weight_type;
@FXML private  MenuItem item_type;
@FXML private RadioButton pidselected;
@FXML private TextField text_pid;
@FXML private MenuButton type_amount;

	
	
	
@FXML private void addcategory(ActionEvent event) throws IOException {
	FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaces/category.fxml"));
	Parent addpro=(Parent) loader.load();
	Hcategory=loader.getController();
	Scene sc= new Scene(addpro);
	stage_category=new Stage();
	stage_category.setScene(sc);
	stage_category.initModality(Modality.APPLICATION_MODAL);
	Image ime= new Image("/icon/almkhtar.png");	
	stage_category.getIcons().add(ime);
	stage_category.show();
	stage_category.setOnHidden(e->{
		lab_choice.setText(Hcategory.getofname());
		checkcategory=Hcategory.getofname();
	});
	
	Hcategory.setStage_category(stage_category);
	
}

private void Alert(String s) {
	 	Alert alert =new Alert(AlertType.WARNING);
	  alert.setTitle("Â‰«ﬂ Œÿ√");
	  alert.setHeaderText(null);
	  alert.setContentText(s);
	  alert.showAndWait();
	  
}


@FXML private void addproductsintosql() throws ClassNotFoundException, SQLException {
	
	if(text_discount.getText().isEmpty()||text_purprice.getText().isEmpty()||
			text_qunt.getText().isEmpty()||text_saleprice.getText().isEmpty()||!pidselected.isSelected()||checkcategory.isEmpty()) {
		if(checkcategory.isEmpty()) {
			Alert("·„  Œ — ‰Ê⁄ ’‰› , —Ã««¡ «Œ Ì«— ‰Ê⁄ ’‰›  ");
		}
		else {
			Alert(".... √ﬂœ „‰  ⁄»∆… «·Ã„Ì⁄ ");
		}
			
		
	}
	else {
	int p_id=0;
	int selected_id=Hcategory.getcatofid();
	qunt=Double.valueOf(text_qunt.getText());
	
	Connection con=ConnectionDB.connectDB();
	PreparedStatement stmt;
	Statement tsmt;
	ResultSet rs1;
	if(pidselected.isSelected()) {
	 stmt=con.prepareStatement("insert into products (barcode,pname,purprice,saleprice,discount,ptype,pamount,pdate,catid)"
			+ "values('"+text_barcode.getText()+"','"+text_name.getText()+"',"+Double.valueOf(text_purprice.getText())+","
					+ ""+Double.valueOf(text_saleprice.getText())+","+Double.valueOf(text_discount.getText())+",'"+Selected_type+"',"+qunt+",'"+text_date.getText()+"',"+selected_id+"); ");
	
	 stmt.executeUpdate();
	  tsmt=con.createStatement();
	  rs1=tsmt.executeQuery("select pid from products where pname='"+text_name.getText()+"';");
	  while (rs1.next()) {
		 p_id=Integer.parseInt(rs1.getString(1)); 
	  }
	  rs1.close();
	  tsmt.close();
	  stmt=con.prepareStatement("update purpriceorderdetails set p_id="+p_id+" where detname='"+Lpurprice.getName()+"'");
		stmt.executeUpdate();
		stmt.close();
		con.close();
		restText_Field() ;
	}
	else {
		stmt=con.prepareStatement("insert into products (pid,barcode,pname,purprice,saleprice,discount,ptype,pamount,pdate,catid)"
				+ "values("+Integer.parseInt(text_pid.getText())+",'"+text_barcode.getText()+"','"+text_name.getText()+"',"+Double.valueOf(text_purprice.getText())+","
						+ ""+Double.valueOf(text_saleprice.getText())+","+Double.valueOf(text_discount.getText())+",'"+Selected_type+"',"+qunt+",'"+text_date.getText()+"',"+selected_id+"); ");
		
		stmt.executeUpdate();
		stmt=con.prepareStatement("update purpriceorderdetails set p_id="+Integer.parseInt(text_pid.getText())+" where detname='"+Lpurprice.getName()+"'");
		stmt.executeUpdate();
		stmt.close();
		con.close();
		restText_Field() ;
	}
	TextVisble(true);
	}
	
}
@FXML private void onlistpurprice() throws IOException {
	FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaces/Listpurprice.fxml"));
	Parent addpro=(Parent) loader.load();
	 Lpurprice=loader.getController();
	Scene sc=new Scene(addpro);
	Stage stage_list=new Stage();
	stage_list.setScene(sc);
	stage_list.initModality(Modality.APPLICATION_MODAL);
	Image ime= new Image("/icon/almkhtar.png");	
	stage_list.getIcons().add(ime);
	stage_list.show();
Lpurprice.setStage_list(stage_list);
stage_list.setOnHidden(e->{
	
	text_name.setText(Lpurprice.getName());
	text_purprice.setText(Lpurprice.getPurprice());
	text_qunt.setText(Lpurprice.getAmount());
	Selected_type=Lpurprice.getTypequnt();
	type_amount.setText(Selected_type);
	
	if(!Lpurprice.getName().isEmpty()) {
	TextVisble(false);
	}

});
checknum.check(text_discount);
checknum.check(text_pid);
checknum.check(text_purprice);
checknum.check(text_qunt);
checknum.check(text_saleprice);

			
}
private void TextVisble(boolean T) {
	text_pid.setDisable(T);
	text_barcode.setDisable(T);
	text_discount.setDisable(T);
	text_name.setDisable(T);
	text_purprice.setDisable(T);
	text_qunt.setDisable(T);
	text_saleprice.setDisable(T);
}

@FXML private void onpidselected(){
	if(selectedpid) {
	text_pid.setDisable(true);
	selectedpid=false;
	}
	else
	{
		text_pid.setDisable(false);	
		selectedpid=true;
		
	}
}
@FXML private void Weight() {
	kgQunt.setVisible(true);
	Gqunt.setVisible(true);
	if(kgQunt.isSelected()) {
	Selected_type="Ê“‰  = ﬂ€„";
	type_amount.setText("Ê“‰  = ﬂ€„");
	}
	else {
	Selected_type="Ê“‰  = €—«„";
	type_amount.setText("Ê“‰  = €—«„");
	}

}
@FXML private void item() {
	kgQunt.setVisible(false);
	Gqunt.setVisible(false);
	Selected_type="Õ»…";
	type_amount.setText("‰Ê⁄ ﬂ„Ì… :Õ»…");
	
}

	private void restText_Field() {
		text_name.setText("");
		text_barcode.setText("");
		text_purprice.setText("");
		text_saleprice.setText("");
		text_discount.setText("");
		text_qunt.setText("");
		
	}
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	Date_pc datepc= new Date_pc();
	text_date.setText(datepc.getdate());
	kgQunt.setOnAction(e->{
		type_amount.setText("Ê“‰  = ﬂ€„");
	});
	Gqunt.setOnAction(e->{
		type_amount.setText("Ê“‰  = €—«„");
	});
}

	

}
