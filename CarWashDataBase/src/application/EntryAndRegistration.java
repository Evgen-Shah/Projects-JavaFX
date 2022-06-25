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

public class EntryAndRegistration {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Entry;

	@FXML
	private Button Registration;

	@FXML
	void initialize() throws SQLException {
		FXMLLoader loader = new FXMLLoader();
		Entry.setOnAction(event -> {// Использование кнопки для входа
			Entry.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Entry_pass.fxml"));

			Loader(loader);
		});

		Registration.setOnAction(event -> {// Использование кнопки для регистрации
			Registration.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Registration.fxml"));

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
