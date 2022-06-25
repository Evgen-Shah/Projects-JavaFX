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

public class Login_window_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField phone;

	@FXML
	private Button reg;

	@FXML
	private TextField birth;

	@FXML
	private Button admin;

	@FXML
	private TextField fio;
	
	@FXML
	private Button users;
	
	@FXML
	private TextField pass;

	public static String name;
	
	public static String phoneNumber;

	Pattern patternB = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{2}");// Патерны для дня рождения
	Pattern patternP = Pattern.compile("([0-9]{11})|([0-9]{10})");//Патерны для телефона
	Base bs = new Base();

	@FXML
	void initialize() {

		FXMLLoader loader = new FXMLLoader();

		reg.setOnAction(event -> {// Действие при нажатии кнопки reg
			Matcher matcherB = patternB.matcher(birth.getText());//Матчеры для проверки
			Matcher matcherP = patternP.matcher(phone.getText());

			
			if(!fio.getText().matches("[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*")) {
				fio.setText("Только буквы");
			}
			else if(!matcherP.matches()) {
				phone.setText("Неверный формат номера телефона!");
			}			
			else if(!matcherB.matches()) {
				birth.setText("Неверный формат даты рождения!");
			}
			else {
				name = fio.getText().split(" ")[1];// Получаем имя из ФИО
				phoneNumber = phone.getText();
				try {
					bs.addClients(fio.getText(), birth.getText(), Long.parseLong(phone.getText()), 0, pass.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reg.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Menu_users.fxml"));
				LoaderFXML(loader);
			}
		});

		users.setOnAction(event -> {// Дейсвтие при нажатии кнопки admin
			users.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Users_enter.fxml"));
			LoaderFXML(loader);

		});
		
		admin.setOnAction(event -> {// Дейсвтие при нажатии кнопки admin
			admin.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Admin_login.fxml"));
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
