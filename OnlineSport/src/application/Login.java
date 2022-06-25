package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button reg;

    @FXML
    private TextField phone;

    @FXML
    private TextField pass;

    @FXML
    private Button enter;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();

    	reg.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
    		reg.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("MenuForUsers.fxml"));
			LoaderFXML(loader);
		});
    	
    	enter.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
    		enter.getScene().getWindow().hide();
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
