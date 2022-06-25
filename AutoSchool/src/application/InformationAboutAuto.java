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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InformationAboutAuto {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Text phone;

	@FXML
	private Button Back;

	@FXML
	private Text adress;
	
	ControleDataBase db = new ControleDataBase();

	@FXML
	void initialize() throws SQLException {
		String[] infoAuto = db.getInfoAuto().split(",");
		phone.setText(infoAuto[0]);
		adress.setText(infoAuto[1]);

		FXMLLoader loader = new FXMLLoader();
		Back.setOnAction(event -> {// Использование кнопки для входа инструктором
			Back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("StyduMainWindow.fxml"));
			phone.setText(infoAuto[0]);
			adress.setText(null);
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
