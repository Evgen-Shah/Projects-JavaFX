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


public class MenuForUsers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button reg;

    @FXML
    private Button check;
    
    @FXML
    private Button back;


    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();
    	
    	reg.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
    		reg.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("SignUp.fxml"));
			LoaderFXML(loader);
		});
    	
    	check.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
    		check.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("CheckVisit.fxml"));
			LoaderFXML(loader);
		});
    	
    	back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
    		back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("LogIn.fxml"));
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
