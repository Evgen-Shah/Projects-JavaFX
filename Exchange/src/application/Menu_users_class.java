package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu_users_class {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button trade;

    @FXML
    private Text name;

    @FXML
    private Button active;

    @FXML
    private Button back;
    
    @FXML
    private Button addBalance;

    @FXML
    private Button history;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();
    	
    	name.setText("Добро пожаловать, " + Login_window_class.name);
    	
    	trade.setOnAction(event -> {// Действие при нажатии кнопки trade
    		trade.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Trade.fxml"));
			LoaderFXML(loader);

		});
    	
    	active.setOnAction(event -> {// Действие при нажатии кнопки active
    		active.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("My_assets.fxml"));
			LoaderFXML(loader);

		});
    	
    	history.setOnAction(event -> {// Действие при нажатии кнопки history
    		history.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("History.fxml"));
			LoaderFXML(loader);

		});
    	
    	back.setOnAction(event -> {// Действие при нажатии кнопки back
    		back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Login_window.fxml"));
			LoaderFXML(loader);

		});
    	
    	addBalance.setOnAction(event -> {// Действие при нажатии кнопки back
    		addBalance.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Payment.fxml"));
			LoaderFXML(loader);

		});
    }
    
	public void LoaderFXML(FXMLLoader loader) {
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
