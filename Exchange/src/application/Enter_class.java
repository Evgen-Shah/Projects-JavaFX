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
import javafx.stage.Stage;

public class Enter_class {

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
	private Button enter;

	Base bs = new Base();

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();

		enter.setOnAction(event -> {// Дейсвтие при нажатии кнопки admin
			try {
				if (bs.checkNumber(phone.getText()).equals(pass.getText())) {
					Login_window_class.phoneNumber = phone.getText();
					Login_window_class.name = bs.getName(phone.getText()).split(" ")[0];
					enter.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("Menu_users.fxml"));
					LoaderFXML(loader);
				}
				else {
					pass.setText("Неверный пароль");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки admin
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Login_window.fxml"));
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
