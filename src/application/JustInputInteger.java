package application;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JustInputInteger {


	
	private void Alert(String s) {
	 	Alert alert =new Alert(AlertType.WARNING);
	  alert.setTitle("åäÇß ÎØÃ");
	  alert.setHeaderText(null);
	  alert.setContentText(s);
	  alert.showAndWait();
	  
}
public void check(TextField textField) {
	
	textField.textProperty().addListener(e->{
		  if (!textField.getText().matches("\\d{0,7}([\\.]\\d{0,4})?")) {
			  textField.setText(textField.getText().substring(0, textField.getText().length()-1));
			  Alert("ÑÌÇÁ ÅÏÎÇá ÇÑÞÇã ÝÞØ !!ð");
			  
			  
	       }
	});

}
}
