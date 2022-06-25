package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller_Study {
	
	public static boolean oneTime = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Recorder;

    @FXML
    private Button Back;

    @FXML
    private Button Options;

    @FXML
    private Button History;
    
    @FXML
    private Button information;

    Order_controller oc = new Order_controller();
    
    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();

		Back.setOnAction(event -> {// Использование кнопки для входа инструктором
			Back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Welcome.fxml"));

			Loader(loader);
		});
		
		Recorder.setOnAction(event -> {// Использование кнопки для входа инструктором
			Recorder.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Order.fxml"));

			Loader(loader);

		});
		
		History.setOnAction(event -> {// Использование кнопки для входа инструктором
			History.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("History.fxml"));

			Loader(loader);
		});
		
		Options.setOnAction(event -> {// Использование кнопки для входа инструктором
			Options.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Update_options.fxml"));

			Loader(loader);
		});
		
		information.setOnAction(event -> {// Использование кнопки для входа инструктором
			information.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("InformationAboutAutoSchool.fxml"));
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
