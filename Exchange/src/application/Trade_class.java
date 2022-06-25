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

public class Trade_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button buy;

	@FXML
	private Button sell;

	@FXML
	private Button back;
	
	public static boolean byuOrSell;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		
		buy.setOnAction(event -> {// Дейсвтие при нажатии кнопки buy
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Choise_assets.fxml"));
			LoaderFXML(loader);
			byuOrSell = true;

		});
		
		sell.setOnAction(event -> {// Дейсвтие при нажатии кнопки sell
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Choise_assets.fxml"));
			LoaderFXML(loader);
			byuOrSell = false;

		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_users.fxml"));
			LoaderFXML(loader);

		});
	}

	public void LoaderFXML(FXMLLoader loader) {
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
