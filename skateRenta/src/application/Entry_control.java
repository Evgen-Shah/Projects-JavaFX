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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Entry_control {

	public static long number;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField phone;

	@FXML
	private TextField pass;

	@FXML
	private Button back;

	@FXML
	private Button entry;

	@FXML
	private Text Wrong;

	BaseControl bs = new BaseControl();

	@FXML
	void initialize() {

		Wrong.setVisible(false);
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("RegAndEntry.fxml"));
			LoaderFXML(loader);

		});

		entry.setOnAction(event -> {// Использование кнопки для входа
			try {
				String passInBase = bs.getPass(phone.getText());
				String passApp = pass.getText();
				if (passApp.equals(passInBase)) {
					entry.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("Options.fxml"));
					LoaderFXML(loader);
					number = Long.parseLong(phone.getText());
					System.out.println(number);
				} else {
					Wrong.setVisible(true);
				}
			} catch (NumberFormatException | SQLException e) {
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
