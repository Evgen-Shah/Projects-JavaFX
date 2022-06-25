package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class CheckVisit {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<?> yors;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("SignUp.fxml"));
			LoaderFXML(loader);
		});
	}

	public void LoaderFXML(FXMLLoader loader) {// Метод для прогрузки окон
		try {
			loader.load();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
