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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Entry_pass_driver {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField Auto;

	@FXML
	private Button Entry;

	@FXML
	private Text passWrong;

	@FXML
	private PasswordField pass;

	DataBaseController db = new DataBaseController();

	@FXML
	private Button Back;

	public static String numberAuto;

	@FXML
	void initialize() throws SQLException {
		passWrong.setVisible(false);
		FXMLLoader loader = new FXMLLoader();
		Back.setOnAction(event -> {// Использование кнопки для выхода назад
			Back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("MainWindows.fxml"));

			Loader(loader);
		});

		Entry.setOnAction(event -> {// Использование кнопки для входа
			numberAuto = Auto.getText();
			try {
				String passInBase = db.checkNum(Auto.getText());
				String passApp = pass.getText();
				if (passApp.equals(passInBase)) {
					Entry.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("ChoiseWindow.fxml"));
					Loader(loader);
				} else {
					passWrong.setVisible(true);
				}
			} catch (NumberFormatException | SQLException e) {

				e.printStackTrace();
			}

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
