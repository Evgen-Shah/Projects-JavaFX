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

public class thanks_buy_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button ok;

	@FXML
	private Text textThanks;

	@FXML
	void initialize() {// Метод при инициализации визуального окна 

		FXMLLoader loader = new FXMLLoader();

		ok.setOnAction(event -> {// Дейсвтие при нажатии кнопки ok
			ok.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("choise_action.fxml"));
			LoaderFXML(loader);
		});

	}

	public void LoaderFXML(FXMLLoader loader) {// Метод для прогрузки окон
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
