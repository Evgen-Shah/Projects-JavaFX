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

public class StyduWindow_controll {
	
	public static boolean oneTimeAddName = true;

	@FXML
	private ResourceBundle resources;


	@FXML
	private Button entry;

	@FXML
	private Button reg;

	@FXML
	private Button Back;
	
    @FXML
    private Button information;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();

		Back.setOnAction(event -> {// Использование кнопки для входа инструктором
			Back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Welcome.fxml"));

			Loader(loader);
		});
		
		reg.setOnAction(event -> {// Использование кнопки для входа инструктором
			reg.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Registration.fxml"));

			Loader(loader);
		});
		
		entry.setOnAction(event -> {// Использование кнопки для входа инструктором
			entry.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("EntryStudy.fxml"));
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
