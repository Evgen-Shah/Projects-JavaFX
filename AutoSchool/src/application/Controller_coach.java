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

public class Controller_coach {
	public static boolean annOneTime = true;
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Cansel;

	@FXML
	private Button Back;

	@FXML
	private Button Recorder;

	@FXML
	private Button Exam;

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
			loader.setLocation(getClass().getResource("CoachOrders.fxml"));

			Loader(loader);
		});

		Cansel.setOnAction(event -> {// Использование кнопки для входа инструктором
			Cansel.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("CancelOrders.fxml"));

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
