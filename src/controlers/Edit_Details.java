package controlers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Edit_Details  implements Initializable{
	@FXML private TextField name;
	@FXML private TextField barcode;
	@FXML private TextField saleprice;
	@FXML private TextField sale;
	@FXML private TextField discount;
	@FXML private TextField type;
	@FXML private TextField qunt;
	@FXML private TextField finalsale;
	@FXML private Button save;
	@FXML private RadioButton KGrum;
	@FXML private RadioButton Grum;
	@FXML private RadioButton items;
	
public void setTextFiled(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7) {
	name.setText(s1);
	barcode.setText(s);
	saleprice.setText(s2);
	qunt.setText(s3);
	type.setText(s4);
	sale.setText(s5);
	discount.setText(s6);
	finalsale.setText(s7);
	
}

public TextField getName() {
	return name;
}

public TextField getBarcode() {
	return barcode;
}

public TextField getSaleprice() {
	return saleprice;
}

public TextField getSale() {
	return sale;
}

public TextField getDiscount() {
	return discount;
}

public TextField getType() {
	return type;
}

public TextField getQunt() {
	return qunt;
}

public TextField getFinalsale() {
	return finalsale;
}

public Button getSave() {
	return save;
}
private double get_Psale() {
	double num;
	if(type.getText().equals("æÒä  = ßÛã")) {
		num=Double.valueOf(saleprice.getText())*Double.valueOf(qunt.getText());
		sale.setText(String.valueOf(num));
		return num;
	}
	else if (type.getText().equals("æÒä  = ÛÑÇã")) {
		num=(Double.valueOf(saleprice.getText())/1000)*Double.valueOf(qunt.getText());
		sale.setText(String.valueOf(num));
		return num;
	}
	else {
		num=Double.valueOf(saleprice.getText())*Double.valueOf(qunt.getText());
		sale.setText(String.valueOf(num));
		return num;
	}
}
private double get_finalsale() {
	double disc= Double.valueOf(discount.getText())/100;
	double sale=get_Psale();
 double  finalsal= sale-(sale*disc);
 return  finalsal;
   
}
	

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
	KGrum.setOnAction(e1->{
		type.setText("æÒä  = ßÛã");
		finalsale.setText(String.valueOf(get_finalsale()));
	});
	Grum.setOnAction(e2->{
		type.setText("æÒä  = ÛÑÇã");
		finalsale.setText(String.valueOf(get_finalsale()));
	});
	items.setOnAction(e3->{
		type.setText("ÍÈÉ");
		finalsale.setText(String.valueOf(get_finalsale()));
	});
 qunt.setOnKeyReleased(e->{
	 finalsale.setText(String.valueOf(get_finalsale()));
 });
	
}	

}