package controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import application.ConnectionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Home_update_Edit {
@FXML private TextField Ename;
@FXML private TextField Ebarcode;
@FXML private TextField Epurprice;
@FXML private TextField EsalePrice;
@FXML private TextField Ediscount;
@FXML private TextField Etype;
@FXML private TextField Equnt;
@FXML private TextField pid;
@FXML private Button save;







public void setvaluesforEdit(String s,String s1,String s2,String s3,String s4,String s5 ,String s6 ,String I) {
	this.Ename.setText(s);
	this.Ebarcode.setText(s1);
	this.Epurprice.setText(s2);
	this.EsalePrice.setText(s3);
	this.Ediscount.setText(s4);
	this.Etype.setText(s5);
	this.Equnt.setText(s6);
	this.pid.setText(I);;
}
public Button getSave() {
	return save;
}


public TextField getEname() {
	return Ename;
}
public TextField getEbarcode() {
	return Ebarcode;
}
public TextField getEpurprice() {
	return Epurprice;
}
public TextField getEsalePrice() {
	return EsalePrice;
}
public TextField getEdiscount() {
	return Ediscount;
}
public TextField getEtype() {
	return Etype;
}
public TextField getEqunt() {
	return Equnt;
}

public TextField getPid() {
	return pid;
}





}
