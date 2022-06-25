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

public class Choise_Driver {
	public static boolean oneTimeAddName = true;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Options;

	@FXML
	private Button Back;

	@FXML
	private Button Service;

	@FXML
	private Button History;

	@FXML
	void initialize() throws SQLException {
		FXMLLoader loader = new FXMLLoader();
		Back.setOnAction(event -> {// Использование кнопки для выхода назад
			Back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("MainWindows.fxml"));

			Loader(loader);
		});

		Service.setOnAction(event -> {// Использование кнопки для выбора услуги
			Service.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("service.fxml"));

			Loader(loader);
		});

		History.setOnAction(event -> {// Использование кнопки для просмотра истории
			History.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("History.fxml"));

			Loader(loader);
		});

		Options.setOnAction(event -> {// Использование кнопки для настроек
			Options.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Update_options.fxml"));

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
