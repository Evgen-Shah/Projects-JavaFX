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

public class retna_ok {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button ok;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		ok.setOnAction(event -> {// Использование кнопки для входа
			try {
				ok.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Options.fxml"));
				LoaderFXML(loader);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

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
