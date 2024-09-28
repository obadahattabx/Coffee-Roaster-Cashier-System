package application;



import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MakDragable {

private double x,y;
private Stage stage;
public  void  makeDragable(AnchorPane parent) {
	parent.setOnMousePressed(e->{
		x=e.getSceneX();
		y=e.getSceneY();
	});
	parent.setOnMouseDragged(e1->{
		stage= (Stage) ((Node)e1.getSource()).getScene().getWindow();
		stage.setX(e1.getScreenX()-x);
		stage.setY(e1.getScreenY()-y);
	});
	
}

}
