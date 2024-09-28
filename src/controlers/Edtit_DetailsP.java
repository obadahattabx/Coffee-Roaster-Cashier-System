package controlers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Edtit_DetailsP implements Initializable {
	@FXML private TextField name;
	
	@FXML private TextField purprice;
	

	@FXML private TextField type;
	@FXML private TextField qunt;
	@FXML private TextField finalsale;
	@FXML private Button save;
	@FXML private RadioButton KGrum;
	@FXML private RadioButton Grum;
	@FXML private RadioButton items;
	
	public void setTextField(String s,String s1,String s2,String s3,String s4) {
		name.setText(s);
		purprice.setText(s1);
		qunt.setText(s2);
		type.setText(s3);
		finalsale.setText(s4);
		
	}
	public TextField getName() {
		return name;
	}
	public void setName(TextField name) {
		this.name = name;
	}

	public TextField getType() {
		return type;
	}
	public void setType(TextField type) {
		this.type = type;
	}
	public TextField getQunt() {
		return qunt;
	}
	public void setQunt(TextField qunt) {
		this.qunt = qunt;
	}
	public TextField getFinalsale() {
		return finalsale;
	}
	public void setFinalsale(TextField finalsale) {
		this.finalsale = finalsale;
	}
	public Button getSave() {
		return save;
	}
	public void setSave(Button save) {
		this.save = save;
	}

	public TextField getPurprice() {
		return purprice;
	}
	public void setPurprice(TextField purprice) {
		this.purprice = purprice;
	}
	private void get_Psale() {
		double num;
		if(type.getText().equals("æÒä  = ßÛã")) {
			num=Double.valueOf(purprice.getText())*Double.valueOf(qunt.getText());
			finalsale.setText(String.valueOf(num));
			
		}
		else if (type.getText().equals("æÒä  = ÛÑÇã")) {
			num=(Double.valueOf(purprice.getText())/1000)*Double.valueOf(qunt.getText());
			finalsale.setText(String.valueOf(num));
			
		}
		else {
			num=Double.valueOf(purprice.getText())*Double.valueOf(qunt.getText());
			finalsale.setText(String.valueOf(num));
			
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		KGrum.setOnAction(e1->{
			type.setText("æÒä  = ßÛã");
			get_Psale() ;		
			});
		Grum.setOnAction(e2->{
			type.setText("æÒä  = ÛÑÇã");
			get_Psale() ;	
			});
		items.setOnAction(e3->{
			type.setText("ÍÈÉ");
			get_Psale() ;	
			});
	 qunt.setOnKeyReleased(e->{
		 get_Psale() ;	
		 });
		
	}	
	
	}
	
	
	
	

