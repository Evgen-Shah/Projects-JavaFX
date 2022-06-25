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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class History_driver {

	DataBaseController db = new DataBaseController();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Back;

	@FXML
	private TextArea Info;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		try {
			Info.appendText(db.getInforamtion());
		} catch (SQLException e) {

			Info.appendText("Пусто");
		}

		Back.setOnAction(event -> {// Назад
			Back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("ChoiseWindow.fxml"));
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
