package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Choise_time {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> time;

    @FXML
    private Button choise;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();
    	
    	choise.setOnAction(event -> {// Назад
    		choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Ok.fxml"));
			Loader(loader);
		});
    	
    	back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("ChoiseTrain.fxml"));
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
