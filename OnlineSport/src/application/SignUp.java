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

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button choise;

    @FXML
    private ChoiceBox<String> train;

    @FXML
    void initialize() {
     	FXMLLoader loader = new FXMLLoader();
     	
     	choise.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
     		choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("ChoiseTimeTrain.fxml"));
			LoaderFXML(loader);
		});
    	
    	back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
    		back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("MenuForUsers.fxml"));
			LoaderFXML(loader);
		});
    }
    
	public void LoaderFXML(FXMLLoader loader) {// Метод для прогрузки окон
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
