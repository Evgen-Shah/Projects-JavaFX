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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registration_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField pass;

	@FXML
	private TextField phone;

	@FXML
	private Button reg;

	@FXML
	private Button back;

	@FXML
	private TextField fullName;

	public static long numberPhone;

	database data = new database();

	Pattern patternPhone = Pattern.compile("([0-9]{11})|([0-9]{10})");// Патерны для телефона

	@FXML
	void initialize() {// Метод при инициализации визуального окна
		FXMLLoader loader = new FXMLLoader();

		reg.setOnAction(event -> {// Дейсвтие при нажатии кнопки reg
			Matcher matcherPhone = patternPhone.matcher(phone.getText());
			try {
				if (data.getCheckHpone(phone.getText())) {
					phone.setText("Уже зарегистрирован!");
				} else if (!fullName.getText().matches("[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*")) {
					fullName.setText("Только буквы");
				} else if (!matcherPhone.matches()) {
					phone.setText("Неверный формат номера телефона!");
				} else {
					numberPhone = Long.parseLong(phone.getText());
					// pin_cord_card_.moneys = 800;
					try {
						data.addClients(Long.parseLong(phone.getText()), fullName.getText(), pass.getText());
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
					reg.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("choise_action.fxml"));
					LoaderFXML(loader);
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("LK.fxml"));
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
