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

public class WelcomeWindow_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button stydu_butt;

    @FXML
    private Button coach_butt;


    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();
    	
    	stydu_butt.setOnAction(event -> {// Использование кнопки для входа студентом
    		stydu_butt.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Study.fxml"));
			Loader(loader);
		});
    	
    	coach_butt.setOnAction(event -> {// Использование кнопки для входа инструктором
    		coach_butt.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("EntryCoach.fxml"));
			Loader(loader);
		});
    	
   
    }
    
	public void Loader(FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
