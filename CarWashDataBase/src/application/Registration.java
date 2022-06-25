package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Registration {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField Auto;

	@FXML
	private TextField Phone_number;

	@FXML
	private Button Button_reg;

	@FXML
	private Button Back;

	@FXML
	private PasswordField pass;

	@FXML
	private TextField SecondName;

	@FXML
	private TextField Name;

	DataBaseController db = new DataBaseController();

	@FXML
	void initialize() throws SQLException {
		FXMLLoader loader = new FXMLLoader();
		Back.setOnAction(event -> {// Использование кнопки назад
			Back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("MainWindows.fxml"));

			Loader(loader);
		});

		Button_reg.setOnAction(event -> {// Использование кнопки для регистрации
			
			String text = Phone_number.getText();
			Pattern pattern = Pattern.compile("[0-9]{10,12}");
			Matcher matcher = pattern.matcher(text);
			boolean b = matcher.matches();

			Entry_pass_driver.numberAuto = Auto.getText();
			if (b) {
				try {
					Button_reg.getScene().getWindow().hide();
					db.addClients(Name.getText(), SecondName.getText(), Long.parseLong(Phone_number.getText()),
							Auto.getText(), Long.parseLong(pass.getText()));
					loader.setLocation(getClass().getResource("ChoiseWindow.fxml"));
					Loader(loader);
				} catch (NumberFormatException | SQLException e) {
					loader.setLocation(getClass().getResource("Registration.fxml"));
					Loader(loader);
				}
			} else {
				Phone_number.setText("Не верный формат");
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
