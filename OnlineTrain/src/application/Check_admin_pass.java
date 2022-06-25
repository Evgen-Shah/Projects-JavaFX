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

public class Check_admin_pass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField pass;

    @FXML
    private Button back;

    @FXML
    private Button enter;
    
    String passForEnter = "1234";

    @FXML
    void initialize() {

		FXMLLoader loader = new FXMLLoader();
    	
		back.setOnAction(event -> {// Регистрация
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Entry.fxml"));
			Loader(loader);
		});

		enter.setOnAction(event -> {// Регистрация
			if(pass.getText().equals(passForEnter)){
			enter.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
			Loader(loader);
			}
			else {
				pass.setText("Неверный пароль");
			}
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
