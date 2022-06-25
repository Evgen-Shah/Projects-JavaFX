package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Sent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();
    	
    	back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu.fxml"));
			Loader(loader);
		});
    }
    
    public void Loader (FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
