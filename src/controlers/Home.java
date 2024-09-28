package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Home implements Initializable {
@FXML private VBox addproducts;
@FXML private VBox listunder;
@FXML private VBox vbBills;
@FXML private VBox underBills;

@FXML private BorderPane borderpan;
private  boolean check=true;
private  boolean check1=true;
private static Stage stagelogin;
private static Stage stagehome;
private static TextField textf;
private static PasswordField passwf;

// button : «·„Œ“‰ 
@FXML private void adds(ActionEvent event) throws IOException {
	Parent addpro=FXMLLoader.load(getClass().getResource("/interfaces/Imagehome.fxml"));
	borderpan.setCenter(addpro);
	if(check) {
		addproducts.setTranslateX(0);
		listunder.setTranslateY(0);
		check=false;
		
	}
	else {
		addproducts.setTranslateX(+300);
		listunder.setTranslateY(-148);
		check=true;
	}
	
	
}
//button : «÷«›… „‰ ÊÃ« 
@FXML private void addproduct() throws IOException {
	Parent addpro=FXMLLoader.load(getClass().getResource("/interfaces/products.fxml"));
	borderpan.setCenter(addpro);
}
// button :  ⁄œÌ· ⁄·Ï „‰ ÊÃ« 
@FXML private void deleteorupdate() throws IOException {
	Parent addupdate=FXMLLoader.load(getClass().getResource("/interfaces/updateordelelte.fxml"));
	borderpan.setCenter(addupdate);
}
// button : «÷«›… „” Œœ„Ì‰ 
@FXML private void addUsers() throws IOException {
	FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaces/Addusers.fxml"));
	Parent addusre=(Parent) loader.load();
	Scene sc= new Scene(addusre);
	Stage st=new Stage();
	st.setScene(sc);
	st.initModality(Modality.APPLICATION_MODAL);
	Image ime= new Image("/icon/almkhtar.png");	
	st.getIcons().add(ime);
	st.show();

	
}
// button : ›« Ê—… «·‘—«¡
@FXML private void billpurprice() throws IOException {
	Parent addusre=FXMLLoader.load(getClass().getResource("/interfaces/BillPruprice.fxml"));
	borderpan.setCenter(addusre);
}
//button : ›« Ê—… «·»Ì⁄
@FXML private void onsaleprice() throws IOException {
	Parent addusre=FXMLLoader.load(getClass().getResource("/interfaces/Billsaleprice.fxml"));
	borderpan.setCenter(addusre);
}
//button : «·›Ê« Ì—
@FXML private void onBills() {

	if(check1) {
		vbBills.setTranslateX(0);
		underBills.setTranslateY(0);
		check1=false;
		
	}
	else {
		vbBills.setTranslateX(+300);
		underBills.setTranslateY(-115);
		check1=true;
	}
}
//button : ›Ê« Ì— «·»Ì⁄
@FXML private void BillsSaleprice() throws IOException {
	Parent addusre=FXMLLoader.load(getClass().getResource("/interfaces/BillsSale.fxml"));
	borderpan.setCenter(addusre);
}
//button : ›Ê« Ì— «·‘—«¡
@FXML private void BillsPure() throws IOException {
	Parent addusre=FXMLLoader.load(getClass().getResource("/interfaces/BillsPure.fxml"));
	borderpan.setCenter(addusre);
}
//button :image exit
@FXML private void onExit() {
	stagehome.close();
	passwf.setText("");
	textf.setText("");
	textf.setManaged(true);
	stagelogin.show();
	
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	Parent addpro;
	try {
		addpro = FXMLLoader.load(getClass().getResource("/interfaces/Imagehome.fxml"));
		borderpan.setCenter(addpro);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// TODO Auto-generated method stub
	addproducts.setTranslateX(+300);
	vbBills.setTranslateX(+300);
	listunder.setTranslateY(-148);
	underBills.setTranslateY(-115);
	
}
public static void setStagelogin(Stage stagelogin,TextField tf,PasswordField pf) {
	Home.stagelogin = stagelogin;
	textf=tf;
	passwf=pf;
}
public static void setStagehome(Stage stagehome) {
	Home.stagehome = stagehome;
}


}
