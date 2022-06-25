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

public class RegAndEntry_driver {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Entry;

	@FXML
	private Button reg;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		Entry.setOnAction(event -> {// Назад
			Entry.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Entry.fxml"));
			LoaderFXML(loader);

		});

		reg.setOnAction(event -> {// регистраиця
			reg.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Registration.fxml"));
			LoaderFXML(loader);

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
