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

public class Options_control {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button cancel;

	@FXML
	private Button change_data;

	@FXML
	private Button back;

	@FXML
	private Button renta_time;

	@FXML
	private Button history;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("RegAndEntry.fxml"));
			LoaderFXML(loader);

		});

		change_data.setOnAction(event -> {// Изменение информации
			change_data.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Settings.fxml"));
			LoaderFXML(loader);

		});

		renta_time.setOnAction(event -> {// Назад
			renta_time.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("information_scate.fxml"));
			LoaderFXML(loader);
			Info_scates.oneTimes = true;

		});

		history.setOnAction(event -> {// Нажатие на историю
			history.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("History.fxml"));
			LoaderFXML(loader);

		});

		cancel.setOnAction(event -> {// отмена заказа
			cancel.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Cancel.fxml"));
			LoaderFXML(loader);
			Cancel_control.addOneTime = true;
		});

	}

	public void LoaderFXML(FXMLLoader loader) {
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
